package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.events.ExpenseEvent;

public interface EventService {
    void handle(ExpenseEvent event);
}
