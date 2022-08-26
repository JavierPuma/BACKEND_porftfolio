package com.pumajavier.portfolio.experiencia;

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
@RequestMapping("/experiencia")
@Tag(name = "Experiencia", description = "Se encarga de las operaciones a realizar en los elementos de Experiencia.")
public class ExperienciaController {

    @Autowired
    ExperienciaService service;

    //Anotaciones para la documentación
    @Operation(summary = "Agregar nueva Experiencia", description = "Sumamos una nueva Experiencia a la BD para el usuario indicado.")
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
    public void agregarExperiencia(@RequestBody ExperienciaDTO exp) {
        service.agregarExperiencia(exp);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Editar Educación", description = "Editamos información de la Experiencia indicada.")
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
    public void editarExperiencia(@PathVariable Long id, @RequestBody ExperienciaDTO datosExperiencia) {
        service.editarExperiencia(id, datosExperiencia);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Listar Experiencias", description = "Obtenemos una lista de toda la Experiencia en la BD, posteriormente se filtran en Frontend.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recursos listados correctamente.")})
    // Anotaciones para el Controller
    @GetMapping("/listar")
    public List<ExperienciaDTO> listarExperiencia() {
        return service.listarExperiencia();
    }

    //Anotaciones para la documentación
    @Operation(summary = "Buscar Experiencia", description = "Obtenemos la información de una Experiencia específica a través de la ID.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso listado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @GetMapping("/{id}")
    public ExperienciaDTO buscarExperiencia(@PathVariable Long id) {
        return service.buscarExperiencia(id);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Eliminar Experiencia", description = "Eliminamos una Experiencia según la ID.")
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
    public void borrarExperiencia(@PathVariable Long id) {
        service.borrarExperiencia(id);
    }
}