package com.pumajavier.portfolio.habilidad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/habilidades")
@Tag(name = "Habilidades", description = "Se encarga de las operaciones a realizar con las Habilidades.")
public class HabilidadController {

    @Autowired
    HabilidadService service;

    //Anotaciones para la documentación
    @Operation(summary = "Agregar una nueva Habilidad", description = "Sumamos una nueva Habilidad al usuario indicado.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso agregado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @PostMapping("/agregar")
    @PreAuthorize("hasRole('ADMIN')")
    public void agregarHabilidad(@RequestBody HabilidadDTO hab) {
        service.agregarHabilidad(hab);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Editar Habilidad", description = "Editamos la información de la Habilidad indicada.")
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
    public void editarHabilidad(@PathVariable Long id, @RequestBody HabilidadDTO datosHabilidad) {
        service.editarHabilidad(id, datosHabilidad);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Buscar Habilidad", description = "Obtenemos la información de una Habilidad específica a través de la ID.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso listado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @GetMapping("/{id}")
    public HabilidadDTO buscarHabilidad(@PathVariable Long id) {
        return service.buscarHabilidad(id);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Listar Habilidades", description = "Obtenemos una lista de todas las Habilidades en la BD, posteriormente se filtran en Frontend.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recursos listados correctamente.")})
    // Anotaciones para el Controller
    @GetMapping("/listar")
    public List<HabilidadDTO> listarHabilidades() {
        return service.listarHabilidades();
    }

    //Anotaciones para la documentación
    @Operation(summary = "Eliminar Habilidad", description = "Eliminamos una Habilidad según la ID.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso eliminado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void borrarHabilidad(@PathVariable Long id) {
        service.borrarHabilidad(id);
    }
}