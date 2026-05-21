package com.Auth.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Auth.service.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@RestController
@CrossOrigin(origins = "*") // Permitir CORS para todas las fuentes (ajusta según tus necesidades)
public class AuthController {

    @Autowired
    private JwtService jwtService;

    private static final String GOOGLE_CLIENT_ID = "your-google-client-id";
    
    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> request) {
        String idTokenString = request.get("idToken");

        try {
            // 1. Configurar el verificador oficial de Google
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                    .build();

            // 2. Verificar que el token no esté alterado ni vencido
            GoogleIdToken idToken = verifier.verify(idTokenString);
            
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");

                // 3. AQUÍ VA TU LÓGICA DE NEGOCIO:
                // TODO: Ir a tu UsuarioRepository y buscar por email.
                // Si no existe, creas el usuario nuevo en la BD usando (email, name, pictureUrl).
                
                // 4. Generar TU propio JWT usando tu servicio existente
                // Nota: Asegúrate de tener un método en tu JwtService que genere el token pasando el email o un UserDetails
                String appToken = jwtService.generateToken(email); 

                // 5. Devolverle la respuesta triunfal al celular
                Map<String, Object> response = new HashMap<>();
                response.put("token", appToken);
                response.put("email", email);
                response.put("name", name);
                response.put("picture", pictureUrl);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Token de Google inválido");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error procesando la autenticación: " + e.getMessage());
        }
    }

}
