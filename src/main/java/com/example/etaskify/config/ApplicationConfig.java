package com.example.etaskify.config;

import com.example.etaskify.dto.response.ExceptionResponse;
import com.example.etaskify.exception.UserNotFoundException;
import com.example.etaskify.provider.DaoAuthenticationProviderDetails;
import com.example.etaskify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
 private UserRepository userRepository;

 @Bean
 public AuthenticationProvider authenticationProvider() {
  DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProviderDetails();
  daoAuthenticationProvider.setUserDetailsService(userDetailsService());
  daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

  return daoAuthenticationProvider;
 }
 @Bean
 @SneakyThrows
 AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
  return authenticationConfiguration.getAuthenticationManager();
 }

 @Bean
 public UserDetailsService userDetailsService() {
  return username -> userRepository.findByUsernameAndEnabled(username)
          .orElseThrow(() -> UserNotFoundException.of(ExceptionResponse.of("NOT_FOUND".getMessage(), "NOT_FOUND".getStatus())));
 }
 @Bean
 PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }
}

