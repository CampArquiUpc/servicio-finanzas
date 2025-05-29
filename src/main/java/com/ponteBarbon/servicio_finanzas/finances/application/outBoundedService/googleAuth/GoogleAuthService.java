package com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleAuth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.SpeechSettings;

import java.io.FileInputStream;
import java.io.IOException;

public interface GoogleAuthService {
    SpeechSettings settingsSpeechToText(FileInputStream fileInputStream) throws IOException;

    GoogleCredentials getGoogleCredentials(FileInputStream fileInputStream) throws IOException;

    String getTokenGoogle(GoogleCredentials googleCredentials);
}
