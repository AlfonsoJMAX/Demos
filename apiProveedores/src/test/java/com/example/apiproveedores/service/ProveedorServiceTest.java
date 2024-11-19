package com.example.apiproveedores.service;

import com.example.apiproveedores.dto.CreateProveedorRequest;
import com.example.apiproveedores.model.Proveedor;
import com.example.apiproveedores.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProveedorServiceTest {

    @InjectMocks
    private ProveedorService proveedorService;

    @Mock
    private ProveedorRepository proveedorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearProveedor() {
        // Arrange
        CreateProveedorRequest request = new CreateProveedorRequest();
        request.setNombre("KAS");
        request.setFechaAlta("2024-11-18");
        request.setIdCliente("5");

        Proveedor proveedorMock = new Proveedor();
        proveedorMock.setIdCliente(5L);
        proveedorMock.setFechaAlta(LocalDate.of(2024, 11, 18));
        proveedorMock.setNombre("KAS");
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorMock);

        // Act
        Proveedor createdProveedor = proveedorService.crearProveedor(request);

        // Assert
        assertNotNull(createdProveedor);
        assertEquals("KAS", createdProveedor.getNombre());
        assertEquals(LocalDate.of(2024, 11, 18), createdProveedor.getFechaAlta());
        assertEquals(5L, createdProveedor.getIdCliente());
    }

    @Test
    void testObtenerProveedoresPorCliente() {
        Long idCliente = 5L;
        
        Proveedor proveedorMock1 = new Proveedor();
        proveedorMock1.setIdCliente(5L);
        proveedorMock1.setFechaAlta(LocalDate.of(2024, 11, 18));
        proveedorMock1.setNombre("KAS");
        
        Proveedor proveedorMock2 = new Proveedor();
        proveedorMock2.setIdCliente(8L);
        proveedorMock2.setFechaAlta(LocalDate.of(2024, 11, 18));
        proveedorMock2.setNombre("Alhambra");
        
        List<Proveedor> mockProveedores = List.of(
        		proveedorMock1,
        		proveedorMock2
        );
        when(proveedorRepository.findByIdCliente(idCliente)).thenReturn(mockProveedores);

        // Act
        List<Proveedor> proveedores = proveedorService.obtenerProveedoresPorCliente(idCliente);

        // Assert
        assertEquals(2, proveedores.size());
    }

    @Test
    void testActualizarProveedor() {
        // Arrange
        Long idProveedor = 1L;
        Long newIdCliente = 10L;
        
        Proveedor existingProveedor = new Proveedor();
        existingProveedor.setIdCliente(5L);
        existingProveedor.setFechaAlta(LocalDate.of(2024, 11, 18));
        existingProveedor.setNombre("KAS");

        when(proveedorRepository.findById(idProveedor)).thenReturn(Optional.of(existingProveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(existingProveedor);

        // Act
        Proveedor updatedProveedor = proveedorService.actualizarProveedor(idProveedor, newIdCliente);

        // Assert
        assertNotNull(updatedProveedor);
        assertEquals(newIdCliente, updatedProveedor.getIdCliente());
    }

    @Test
    void testEliminarProveedor() {
        // Arrange
        Long idProveedor = 1L;
        doNothing().when(proveedorRepository).deleteById(idProveedor);

        // Act
        proveedorService.eliminarProveedor(idProveedor);

        // Assert
        verify(proveedorRepository, times(1)).deleteById(idProveedor);
    }
}
