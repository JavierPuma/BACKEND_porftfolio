package com.pumajavier.portfolio.seccion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/secciones")
@Tag(name = "Secciones", description = "Se encarga de manejar la información de secciones como Proyectos, Habilidades, etc.")
public class SeccionController {

    @Autowired
    SeccionService service;

    // Ignoramos en la documentación ya que este método se utiliza únicamente para crear las secciones por defecto.
    @Operation(summary = "Crear una nueva sección", description = "Éste endpoint se utiliza únicamente al momento de registrar un usuario para generar las Secciones por defecto. NO usar en otros contextos.", hidden = true)
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public void crearSeccion(@RequestBody SeccionDTO sec) {
        service.crearSeccion(sec);
    }

    // Anotaciones para documentar la API
    @Operation(summary = "Listar Secciones", description = "Obtenemos un listado de todas las Secciones disponibles, posteriormente son filtradas en el Frontend.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recursos listados correctamente.")})
    @GetMapping("/listar")
    public List<SeccionDTO> listarSecciones() {
        return service.listarSecciones();
    }

    // Anotaciones para documentar la API
    @Operation(summary = "Buscar Seccion", description = "Obtenemos la información de una Sección específico a través de la ID.")
    @SecurityRequirement(name = "Authorization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso listado correctamente."),
        @ApiResponse(responseCode = "400", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "401", description = "Usuario no autorizado, inicie sesión."),
        @ApiResponse(responseCode = "403", description = "No se puede acceder a este recurso."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller

    @GetMapping("/{id}")
    public SeccionDTO buscarSeccion(@PathVariable Long id) {
        return service.buscarSeccion(id);
    }

    // Anotaciones para documentar la API
    @Operation(summary = "Editar Seccion", description = "Editamos los datos de una Sección en específico.")
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
    public void editarSeccion(@PathVariable Long id, @RequestBody SeccionDTO sec) {
        service.editarSeccion(id, sec);
    }

    // Ocultamos el método ya que no se utiliza debido a que las secciones no se eliminan. 
    @Operation(hidden = true)
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarSeccion(@PathVariable Long id) {
        service.eliminarSeccion(id);
    }
}