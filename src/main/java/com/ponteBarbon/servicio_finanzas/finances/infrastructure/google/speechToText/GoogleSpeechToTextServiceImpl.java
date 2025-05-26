package com.ponteBarbon.servicio_finanzas.finances.infrastructure.google.speechToText;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleSpeechToText.GoogleSpeechToTextService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GoogleSpeechToTextServiceImpl implements GoogleSpeechToTextService {
    @Override
    public RecognitionConfig conf(Integer rateHertz, String language) {
        return RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setSampleRateHertz(rateHertz)
                .setLanguageCode(language)
                .build();
    }

    @Override
    public RecognitionAudio audio(byte[] data) {
        return RecognitionAudio.newBuilder()
                .setContent(ByteString.copyFrom(data))
                .build();
    }

    @Override
    public RecognizeResponse response(SpeechClient speechClient, RecognitionConfig recognitionConfig, RecognitionAudio recognitionAudio) {
        return speechClient.recognize(recognitionConfig, recognitionAudio);
    }



    @Override
    public String transcription(RecognizeResponse recognizeResponse) {
        return recognizeResponse.getResultsList().stream()
                .flatMap(r -> r.getAlternativesList().stream())
                .map(SpeechRecognitionAlternative::getTranscript)
                .collect(Collectors.joining(" "));
    }
}
