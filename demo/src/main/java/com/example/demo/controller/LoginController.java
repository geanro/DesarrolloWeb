package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    // Inyección por constructor
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, @RequestParam String password) {
        
        boolean esValido = loginService.autenticarUsuario(username, password);
        
        if (esValido) {
            return "¡Login exitoso! Bienvenido al sistema.";
        } else {
            return "Error: Usuario o contraseña incorrectos.";
        }
    }
}