package com.Utp.DesarrolloWeb.service;


import com.Utp.DesarrolloWeb.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        // Datos en duro
        clientes.add(new Cliente(1, "Ana",    "García",   "ana@gmail.com",    "987654321"));
        clientes.add(new Cliente(2, "Luis",   "Pérez",    "luis@gmail.com",   "912345678"));
        clientes.add(new Cliente(3, "María",  "Torres",   "maria@gmail.com",  "998877665"));
    }

    public List<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Cliente guardar(Cliente c) {
        clientes.add(c);
        return c;
    }

    public boolean eliminar(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}