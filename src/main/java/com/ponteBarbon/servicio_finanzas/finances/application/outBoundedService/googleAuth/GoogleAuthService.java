package com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleAuth;

import com.google.cloud.speech.v1.SpeechSettings;

import java.io.FileInputStream;
import java.io.IOException;

public interface GoogleAuthService {
    SpeechSettings settings(FileInputStream fileInputStream) throws IOException;
}
