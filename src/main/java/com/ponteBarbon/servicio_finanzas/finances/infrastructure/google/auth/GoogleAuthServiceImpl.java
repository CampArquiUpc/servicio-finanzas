package com.ponteBarbon.servicio_finanzas.finances.infrastructure.google.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1.SpeechSettings;
import com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleAuth.GoogleAuthService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {
    @Override
    public SpeechSettings settingsSpeechToText(FileInputStream fileInputStream) throws IOException {
        return SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> ServiceAccountCredentials
                        .fromStream(fileInputStream))
                .build();
    }

    @Override
    public GoogleCredentials getGoogleCredentials(FileInputStream fileInputStream) throws IOException {
        return  ServiceAccountCredentials.fromStream(fileInputStream)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));
    }

    @Override
    public String getTokenGoogle(GoogleCredentials googleCredentials) {
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
