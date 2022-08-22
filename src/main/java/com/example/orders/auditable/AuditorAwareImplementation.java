package com.example.orders.auditable;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor()
    {
        String currentUser= SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(currentUser);
    }
}
