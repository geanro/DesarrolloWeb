package com.Utp.DesarrolloWeb.controller;

import com.Utp.DesarrolloWeb.model.Pedido;
import com.Utp.DesarrolloWeb.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearPedidoDesdeCarrito() {
        try {
            Pedido pedido = pedidoService.crearPedidoDesdeCarrito();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("error: " +e.getMessage());
        }
    }
    @GetMapping("/traer")
    public ResponseEntity<?> traerPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPedido(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.buscarPedido(id));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/total/{monto}")
    public ResponseEntity<?> buscarPorMontoMayor(@PathVariable double monto){
        List<Pedido> pedidos = pedidoService.buscarPorMontoMayor(monto);
        if(pedidos.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron pedidos con monto mayor a " + monto);
        }
        return ResponseEntity.ok(pedidos);
    }
}