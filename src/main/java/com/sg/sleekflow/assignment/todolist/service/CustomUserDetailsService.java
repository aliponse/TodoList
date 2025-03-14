package com.sg.sleekflow.assignment.todolist.service;

import com.sg.sleekflow.assignment.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.sg.sleekflow.assignment.todolist.model.User rs = userRepository.findByUsername(username).orElse(null);

        if (rs == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(rs.getUsername())
                .password(rs.getPassword())
                .roles("")
                .build();
    }
}