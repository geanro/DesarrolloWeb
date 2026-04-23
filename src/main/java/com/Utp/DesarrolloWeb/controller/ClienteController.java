package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.model.Cliente;
import com.Utp.DesarrolloWeb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    @GetMapping
    public List<Cliente> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente c) {
        return servicio.guardar(c);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean eliminado = servicio.eliminar(id);
        return eliminado ? "Cliente eliminado" : "Cliente no encontrado";
    }
}