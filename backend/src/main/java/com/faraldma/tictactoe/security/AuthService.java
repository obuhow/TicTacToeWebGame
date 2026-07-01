package com.faraldma.tictactoe.security;

import com.faraldma.tictactoe.datasource.model.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.faraldma.tictactoe.datasource.repository.UserRepository;
import com.faraldma.tictactoe.exception.UserAlreadyExistsException;
import com.faraldma.tictactoe.web.model.SignUpRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void register(SignUpRequest request) {
        if (userRepository.findUserByLogin(request.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException(request.getLogin());
        }
        User user = new User(null, request.getLogin(), passwordEncoder.encode(request.getPassword()), null);
        userRepository.save(user);
    }

    public Authentication authenticate(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            throw new AuthorizationServiceException("Authorization failed");
        }
        String encodedCreds = authHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCreds);
        String decodedCreds = new String(decodedBytes, StandardCharsets.UTF_8);
        String[] credsParts = decodedCreds.split(":", 2);

        String login = credsParts[0];
        String password = credsParts[1];

        User user = userRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthorizationServiceException("Wrong password");
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    public UUID getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getId();
    }
}
