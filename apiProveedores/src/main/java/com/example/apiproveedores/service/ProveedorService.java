package com.example.apiproveedores.service;

import com.example.apiproveedores.dto.CreateProveedorRequest;
import com.example.apiproveedores.model.Proveedor;
import com.example.apiproveedores.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Proveedor crearProveedor(CreateProveedorRequest request) {
    	
    	Proveedor proveedor = new Proveedor();
    	proveedor.setNombre(request.getNombre());
    	proveedor.setFechaAlta(LocalDate.parse(request.getFechaAlta())); // Convertimos String a LocalDateTime
    	proveedor.setIdCliente(Long.valueOf(request.getIdCliente()));
        return proveedorRepository.save(proveedor);
    }
    
    public List<Proveedor> obtenerProveedoresPorCliente(Long idCliente) {
        return proveedorRepository.findByIdCliente(idCliente);
    }

    public Optional<Proveedor> obtenerProveedorPorId(Long idProveedor) {
        return proveedorRepository.findById(idProveedor);
    }

    public Proveedor actualizarProveedor(Long idProveedor, Long idCliente) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(idProveedor);
        if (optionalProveedor.isPresent()) {
            Proveedor proveedor = optionalProveedor.get();
            proveedor.setIdCliente(idCliente);
            return proveedorRepository.save(proveedor);
        }
        return null;
    }

    public void eliminarProveedor(Long idProveedor) {
        proveedorRepository.deleteById(idProveedor);
    }
}
