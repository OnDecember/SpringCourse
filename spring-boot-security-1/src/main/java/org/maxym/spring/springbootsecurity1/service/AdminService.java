package org.maxym.spring.springbootsecurity1.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ADMIN')")
    public void doAdmin() {
        System.out.println("Admin Here!");
    }
}
