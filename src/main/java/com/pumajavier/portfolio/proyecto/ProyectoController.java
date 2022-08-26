package com.pumajavier.portfolio.proyecto;

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
@RequestMapping("/proyectos")
@Tag(name = "Proyectos", description = "Se encarga de las operaciones a realizar en los Proyectos.")
public class ProyectoController {

    @Autowired
    ProyectoService service;

    //Anotaciones para la documentación
    @Operation(summary = "Agregar un nuevo Proyecto", description = "Sumamos un nuevo Proyecto a la BD para el usuario indicado.")
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
    public void agregarProyecto(@RequestBody ProyectoDTO proj) {
        service.agregarProyecto(proj);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Editar Proyecto", description = "Editamos la información del Proyecto indicado.")
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
    public void editarProyecto(@PathVariable Long id, @RequestBody ProyectoDTO datosProyecto) {
        service.editarProyecto(id, datosProyecto);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Buscar Proyecto", description = "Obtenemos la información de un Proyecto específico a través de la ID.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso listado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @GetMapping("/{id}")
    public ProyectoDTO buscarProyecto(@PathVariable Long id) {
        return service.buscarProyecto(id);
    }

    //Anotaciones para la documentación
    @Operation(summary = "Listar Proyectos", description = "Obtenemos una lista de todos los Proyectos en la BD, posteriormente se filtran en Frontend.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recursos listados correctamente.")})
    // Anotaciones para el Controller
    @GetMapping("/listar")
    public List<ProyectoDTO> listarProyectos() {
        return service.listarProyectos();
    }

    //Anotaciones para la documentación
    @Operation(summary = "Eliminar Proyecto", description = "Eliminamos un Proyecto según la ID.")
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
    public void borrarProyecto(@PathVariable Long id) {
        service.borrarProyecto(id);
    }
}