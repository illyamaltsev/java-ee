package com.website.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.website.demo.config.MyPasswordEncoder;
import com.website.demo.domain.entities.PermissionEntity;
import com.website.demo.domain.entities.UserEntity;
import com.website.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + username));

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(mapAuthorities(user.getPermissions()))
                .build();
    }

    private static List<GrantedAuthority> mapAuthorities(final List<PermissionEntity> permissions) {
        return permissions.stream()
                .map(PermissionEntity::getPermission)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean saveUser(User user) {
        Optional<UserEntity> dbUser = userRepository.findByLogin(user.getUsername());

        if (dbUser.isPresent()) {
            return false;
        }

        UserEntity newUser = new UserEntity();

        newUser.setLogin(user.getUsername());
        newUser.setPassword(new MyPasswordEncoder().encode(user.getPassword()));
        userRepository.saveAndFlush(newUser);

        return true;
    }
}
