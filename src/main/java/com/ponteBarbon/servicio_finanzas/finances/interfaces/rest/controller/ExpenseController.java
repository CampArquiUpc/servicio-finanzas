package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.controller;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.speech.v1.*;

import com.google.protobuf.ByteString;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.CreateExpenseResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.ExpenseResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/expense", produces = APPLICATION_JSON_VALUE)
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(value = "/audio",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> AddedExpenseByAudio(
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        File savedFile = new File("F:\\audios\\", file.getOriginalFilename());
        savedFile.getParentFile().mkdirs();
        file.transferTo(savedFile);



        SpeechSettings settings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> ServiceAccountCredentials
                        .fromStream(new FileInputStream("F:\\audios\\pontebarbon-7a8ae86045da.json")))
                .build();

        try(SpeechClient speechClient = SpeechClient.create(settings)) {
            byte[] data = Files.readAllBytes(savedFile.toPath());

            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(data))
                    .build();

            RecognitionConfig conf = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(48000)
                    .setLanguageCode("es-PE")
                    .build();

            RecognizeResponse response = speechClient.recognize(conf,audio);

            String transcript = response.getResultsList().stream()
                    .flatMap(r -> r.getAlternativesList().stream())
                    .map(SpeechRecognitionAlternative::getTranscript)
                    .collect(Collectors.joining(" "));

            return ResponseEntity.ok("Texto reconocido: " + transcript);
        }

    }

    @PostMapping
    public ResponseEntity<ExpenseResource> AddedExpense(@RequestBody CreateExpenseResource resource){
        var createExpenseCommand =
                CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);
        var expenseId = expenseService.handle(createExpenseCommand);

        if (expenseId == 0L) return ResponseEntity.notFound().build();

        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseService.handle(getExpenseByIdQuery);

        var expenseResource = ExpenseResourceFromEntityAssembler
                .toResourceFromEntity(expense.get());

        return ResponseEntity.ok(expenseResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResource> GetExpenseById(@PathVariable Long id){
        var getExpenseByIdQuery = new GetExpenseByIdQuery(id);
        var expense = expenseService.handle(getExpenseByIdQuery);

        if (expense.isEmpty()) return ResponseEntity.notFound().build();

        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());

        return ResponseEntity.ok(expenseResource);
    }
}
