package com.Auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.GoogleLoginAuthResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.models.Usuario;
import com.service.GoogleAuthService;
import com.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login-google")
    public ResponseEntity<GoogleLoginAuthResponse> loginConGoogle(@RequestBody Map<String, String> request) {
        try {
            String idToken = request.get("idToken");

            // Verificar token con Google
            GoogleIdToken.Payload payload = googleAuthService.verifyToken(idToken);

            // Obtener datos del usuario desde el payload
            String email = payload.getEmail();
            String nombre = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String googleId = payload.getSubject();

            // Buscar o registrar usuario
            Usuario usuario = usuarioService.registrarOLoginConGoogle(email, nombre, googleId, pictureUrl);

            // Opcional: Generar JWT propia para tu app
            String tokenApp = jwtService.generateToken(usuario);

            // Preparar respuesta DTO
            GoogleLoginAuthResponse response = new GoogleLoginAuthResponse("Login exitoso", usuario.getId(),
                    usuario.getEstado(), null);
            response.setToken(tokenApp);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(new GoogleLoginAuthResponse("Error al iniciar sesión", null, null, null));
        }
    }
}
