package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");

        // Validación simple — puedes conectarlo a tu LoginService
        if ("admin".equals(username) && "1234".equals(password)) {
            String token = jwtUtil.generarToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body(Map.of("error",
                "Credenciales incorrectas"));
    }
}