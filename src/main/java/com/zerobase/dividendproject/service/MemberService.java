package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.model.Auth;
import com.zerobase.dividendproject.model.MemberEntity;
import com.zerobase.dividendproject.persist.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("could not find user with username: " + username));
    }

    public MemberEntity register(Auth.SignUp member) {
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());

        if (exists) {
            throw new RuntimeException("username already exists");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        var result = this.memberRepository.save(member.toEntity());
        return result;
    }

    public MemberEntity authenticate(Auth.SignIn member) {

        var user = this.memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("could not find user with username: " + member.getUsername()));

        if (this.passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new RuntimeException("password does not match");
        }

        return user;

    }
}
