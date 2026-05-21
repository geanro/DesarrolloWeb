package com.Utp.DesarrolloWeb.service;

import com.Utp.DesarrolloWeb.model.Cliente;
import com.Utp.DesarrolloWeb.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(int id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente guardar(Cliente c) {
        return repository.save(c);
    }

    public boolean eliminar(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}