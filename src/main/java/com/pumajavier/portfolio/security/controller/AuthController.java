package com.pumajavier.portfolio.security.controller;


import com.pumajavier.portfolio.educacion.EducacionService;
import com.pumajavier.portfolio.experiencia.ExperienciaService;
import com.pumajavier.portfolio.habilidad.HabilidadService;
import com.pumajavier.portfolio.persona.Persona;
import com.pumajavier.portfolio.persona.PersonaService;
import com.pumajavier.portfolio.proyecto.ProyectoService;
import com.pumajavier.portfolio.seccion.SeccionService;
import com.pumajavier.portfolio.security.entity.ERole;
import com.pumajavier.portfolio.security.entity.Role;
import com.pumajavier.portfolio.security.entity.User;
import com.pumajavier.portfolio.security.jwt.JwtUtils;
import com.pumajavier.portfolio.security.payload.JwtResponse;
import com.pumajavier.portfolio.security.payload.LoginRequest;
import com.pumajavier.portfolio.security.payload.MessageResponse;
import com.pumajavier.portfolio.security.payload.RegisterRequest;
import com.pumajavier.portfolio.security.repository.RoleRepository;
import com.pumajavier.portfolio.security.repository.UserRepository;
import com.pumajavier.portfolio.security.service.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Se encarga del Registro e Ingreso de usuarios.")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SeccionService seccionService;
    @Autowired
    PersonaService personaService;
    @Autowired
    EducacionService educacionService;
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    HabilidadService habilidadService;
    @Autowired
    ProyectoService proyectoService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    //Anotaciones para la documentación
    @Operation(summary = "Ingresar a la cuenta", description = "Se encarga de validar los datos ingresados y generar el JWT que será usado por el Frontend.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario ingresó con éxito."),
        @ApiResponse(responseCode = "401", description = "Hubo un problema en la solicitud, revise los datos ingresados."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Bad Credentials"));
        }
    }

    //Anotaciones para la documentación
    @Operation(summary = "Registrar un nuevo usuario", description = "Se encarga de crear un nuevo usuario en la DB, así como todas los campos por defecto.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario registrado con éxito."),
        @ApiResponse(responseCode = "401", description = "Hubo un problema en la solicitud."),
        @ApiResponse(responseCode = "404", description = "No se encontró el recurso especificado.")})
    // Anotaciones para el Controller
    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: ¡Este nombre de usuario ya fue utilizado!"));
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: ¡Este e-mail ya fue utilizado!"));
        }

        // Creamos un nuevo usuario
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));
        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: No se encontró el rol especificado."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol especificado."));
                        roles.add(userRole);
                        break;
                    default:
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol especificado."));
                        roles.add(adminRole);

                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        // Creamos una nueva entrada en "Persona" cuando nos registramos, a modo de placeholder. TODO: Enlazar las demas tablas a persona y modificar la ID en el front.
        Persona nuevaPersona = new Persona(user, "Nombre y Apellido", "Titulo", "https://www.linkedin.com", "https://github.com", "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png", "https://cabaretfestival.es/wp-content/uploads/2020/07/Hero-Banner-Placeholder-Light-1024x480-1.png", "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        personaService.crearPersona(nuevaPersona);
        seccionService.crearSeccionesDefault(user.getId());
        educacionService.crearEducacionDefault(user.getId());
        experienciaService.crearExperienciaDefault(user.getId());
        habilidadService.crearHabilidadDefault(user.getId());
        proyectoService.crearProyectoDefault(user.getId());

        return ResponseEntity.ok(new MessageResponse("¡El usuario se registró con éxito!"));
    }
}