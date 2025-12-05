package com.service.serviceImp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.service.GoogleAuthService;
import java.util.Collections;

@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {

    private final String CLIENT_ID = "407408718192.apps.googleusercontent.com";
    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    public GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID token");
        }

    }

}
