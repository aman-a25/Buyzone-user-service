package com.buyzone.user_service.service;

import com.buyzone.user_service.exception.IdentifierNotFoundException;
import com.buyzone.user_service.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    CustomUserDetailsServiceImp( UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String identifier) throws IdentifierNotFoundException {
        return userRepository.findByEmailOrPhone(identifier , identifier).orElseThrow(() -> new IdentifierNotFoundException("the given Identifier is not a valid Identifier (Phone NO. without country code / Email)"));
    }

}
