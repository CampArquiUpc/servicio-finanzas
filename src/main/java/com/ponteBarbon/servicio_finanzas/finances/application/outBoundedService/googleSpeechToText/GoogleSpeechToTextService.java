package com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleSpeechToText;

import com.google.cloud.speech.v1.*;

public interface GoogleSpeechToTextService {
    RecognitionConfig conf(Integer rateHertz,String language);
    RecognitionAudio audio(byte[] data);
    RecognizeResponse response(
            SpeechClient speechClient,
            RecognitionConfig recognitionConfig,
            RecognitionAudio recognitionAudio);

    String transcription(RecognizeResponse recognizeResponse);
}
