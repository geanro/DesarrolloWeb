package com.Utp.DesarrolloWeb.controller;
import com.Utp.DesarrolloWeb.model.Cliente;
import com.Utp.DesarrolloWeb.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService servicio;
    public ClienteController(ClienteService servicio) { this.servicio = servicio; }
    @GetMapping public List<Cliente> listar() { return servicio.listar(); }
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable int id) { // Si tu modelo usa int, está bien, pero asegúrate que JPA encuentre int
        return servicio.buscarPorId(id);
    }
    @PostMapping public Cliente guardar(@RequestBody Cliente c) { return servicio.guardar(c); }
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean eliminado = servicio.eliminar(id);
        return eliminado ? "Cliente eliminado" : "Cliente no encontrado";
    }
}