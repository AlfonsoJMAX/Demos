package com.example.apiproveedores.controller;

import com.example.apiproveedores.model.Proveedor;
import com.example.apiproveedores.service.ProveedorService;
import com.example.apiproveedores.dto.CreateProveedorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody CreateProveedorRequest request) {
        Proveedor createdProveedor = proveedorService.crearProveedor(request);
        return ResponseEntity.ok(createdProveedor);
    }
    
    @GetMapping("/{idCliente}")
    public ResponseEntity<List<Proveedor>> obtenerProveedoresPorCliente(@PathVariable Long idCliente) {
        List<Proveedor> proveedores = proveedorService.obtenerProveedoresPorCliente(idCliente);
        return ResponseEntity.ok(proveedores);
    }

    @PutMapping("/{idProveedor}/{idCliente}")
    public ResponseEntity<Proveedor> actualizarProveedor(
            @PathVariable Long idProveedor, @PathVariable Long idCliente) {
        Proveedor proveedorActualizado = proveedorService.actualizarProveedor(idProveedor, idCliente);
        if (proveedorActualizado != null) {
            return ResponseEntity.ok(proveedorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idProveedor}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long idProveedor) {
        proveedorService.eliminarProveedor(idProveedor);
        return ResponseEntity.noContent().build();
    }
}
