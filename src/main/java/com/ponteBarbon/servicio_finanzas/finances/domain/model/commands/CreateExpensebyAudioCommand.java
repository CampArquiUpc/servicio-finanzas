package com.ponteBarbon.servicio_finanzas.finances.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public record CreateExpensebyAudioCommand(MultipartFile data) {
}
