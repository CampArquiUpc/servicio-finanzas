package com.ponteBarbon.servicio_finanzas.finances.application;


import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleAuth.GoogleAuthService;
import com.ponteBarbon.servicio_finanzas.finances.application.outBoundedService.googleSpeechToText.GoogleSpeechToTextService;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.Expense;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpenseCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateExpensebyAudioCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetExpenseByIdUserQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.ExpenseService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final GoogleAuthService googleAuthService;
    private final GoogleSpeechToTextService googleSpeechToTextService;


    public ExpenseServiceImpl(ExpenseRepository expenseRepository, GoogleAuthService googleAuthService, GoogleSpeechToTextService googleSpeechToTextService) {
        this.expenseRepository = expenseRepository;
        this.googleAuthService = googleAuthService;
        this.googleSpeechToTextService = googleSpeechToTextService;
    }


    @Override
    public List<Expense> handle(GetExpenseByIdUserQuery query) {

        return expenseRepository.findAll();
    }

    @Override
    public Long handle(CreateExpenseCommand command) {

        try{
            Expense expense = new Expense(
                    command.description(),
                    command.type(),
                    command.amount(),
                    command.dateOfExpense());
            expenseRepository.save(expense);
            return expense.getId();
        } catch(Exception e){
            throw new IllegalArgumentException("Error while saving expense" + e.getMessage());
        }




    }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query) {

        return expenseRepository.getExpenseById(query.id());
    }

    @Override
    public String handle(CreateExpensebyAudioCommand command) throws IOException {

        String typeData = command.data().getContentType();

        if(typeData == null || !typeData.equals("audio/wav") && !typeData.equals("audio/x-wav")){
            throw new IllegalArgumentException("El Tipo de Audio no esta permitido tiene que ser wav");
        }


        var fileBytes = command.data().getBytes();



        var authGoogle = googleAuthService.settings(new FileInputStream("F:\\audios\\pontebarbon-a4bd39860683.json"));

        try(SpeechClient speechClient = SpeechClient.create(authGoogle)) {

            var audio = googleSpeechToTextService.audio(fileBytes);
            var conf = googleSpeechToTextService.conf(48000,"es-Pe");
            var response = googleSpeechToTextService.response(speechClient,conf,audio);

            return googleSpeechToTextService.transcription(response);
        }

    }
}
