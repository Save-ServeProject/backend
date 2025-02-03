package com.example.saveandserve.demo.service;

import com.example.saveandserve.demo.entity.Administrador;
import com.example.saveandserve.demo.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return administradorRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado: " + username));
    }
}
