package com.pumajavier.portfolio.persona;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/personas")
@Tag(name = "Persona", description = "Maneja los Datos Personales de cada usuario.")
public class PersonaController {

    @Autowired
    PersonaService service;

    //Anotaciones para la documentación
    @Operation(summary = "Buscar Persona", description = "Obtenemos los Datos Personales del usuario indicado (ID).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso listado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @GetMapping("/{id}")
    public Persona buscarPersona(@PathVariable Long id) {
        return service.buscarPersona(id);
    }

    // Ocultamos en la documentación ya que sólo se usa al registrar un usuario.
    @Operation(hidden = true)
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public void crearPersona(@RequestBody Persona pers) {
        service.crearPersona(pers);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Editar Persona", description = "Editamos la información personal del usuario.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void editarPersona(@PathVariable Long id, @RequestBody Persona datosPersona) {
        service.editarPersona(id, datosPersona);
    }
}