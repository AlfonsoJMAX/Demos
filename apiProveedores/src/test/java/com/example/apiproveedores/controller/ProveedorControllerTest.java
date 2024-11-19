package com.example.apiproveedores.controller;

import com.example.apiproveedores.dto.CreateProveedorRequest;
import com.example.apiproveedores.model.Proveedor;
import com.example.apiproveedores.service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @BeforeEach
    void setUp() {
        // Setup can go here if needed
    }

    @Test
    void testCreateProveedor() throws Exception {
        // Arrange
        CreateProveedorRequest request = new CreateProveedorRequest();
        request.setNombre("KAS");
        request.setFechaAlta("2024-11-18");
        request.setIdCliente("5");

        Proveedor mockProveedor = new Proveedor();
        mockProveedor.setIdCliente(5L);
        mockProveedor.setFechaAlta(LocalDate.of(2024, 11, 18));
        mockProveedor.setNombre("KAS");

       // when(proveedorService.crearProveedor(any(CreateProveedorRequest.class))).thenReturn(mockProveedor);
        when(proveedorService.crearProveedor((CreateProveedorRequest) any())).thenReturn(mockProveedor);

        // Act & Assert
        mockMvc.perform(post("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"KAS\", \"fechaAlta\":\"2024-11-18\", \"idCliente\":\"5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("KAS"))
                .andExpect(jsonPath("$.idCliente").value(5));
    }

    @Test
    void testObtenerProveedoresPorCliente() throws Exception {
        // Arrange
        Long idCliente = 5L;
        
        Proveedor mockProveedor1 = new Proveedor();
        mockProveedor1.setIdCliente(5L);
        mockProveedor1.setFechaAlta(LocalDate.of(2024, 11, 18));
        mockProveedor1.setNombre("KAS");
        
        Proveedor mockProveedor2 = new Proveedor();
        mockProveedor2.setIdCliente(8L);
        mockProveedor2.setFechaAlta(LocalDate.of(2024, 11, 18));
        mockProveedor2.setNombre("Alhambra");
        
        
        List<Proveedor> mockProveedores = List.of(
        		mockProveedor1,
        		mockProveedor2
        );
        when(proveedorService.obtenerProveedoresPorCliente(idCliente)).thenReturn(mockProveedores);

        // Act & Assert
        mockMvc.perform(get("/api/proveedores/{idCliente}", idCliente))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("KAS"))
                .andExpect(jsonPath("$[1].nombre").value("Alhambra"));
    }

    @Test
    void testActualizarProveedor() throws Exception {
        // Arrange
        Long idProveedor = 1L;
        Long newIdCliente = 10L;
        
        Proveedor updatedProveedor = new Proveedor();
        updatedProveedor.setIdProveedor(idProveedor);
        updatedProveedor.setIdCliente(newIdCliente);
        updatedProveedor.setFechaAlta(LocalDate.of(2024, 11, 18));
        updatedProveedor.setNombre("KAS");

        when(proveedorService.actualizarProveedor(idProveedor, newIdCliente)).thenReturn(updatedProveedor);

        // Act & Assert
        mockMvc.perform(put("/api/proveedores/{idProveedor}/{idCliente}", idProveedor, newIdCliente))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCliente").value(newIdCliente));
    }

    @Test
    void testEliminarProveedor() throws Exception {
        // Arrange
        Long idProveedor = 1L;
        doNothing().when(proveedorService).eliminarProveedor(idProveedor);

        // Act & Assert
        mockMvc.perform(delete("/api/proveedores/{idProveedor}", idProveedor))
                .andExpect(status().isNoContent());
    }
}

