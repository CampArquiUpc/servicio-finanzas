package com.ponteBarbon.servicio_finanzas.finances.domain.model.queries;

public class GetExpensesByUserIdQuery {
    private final Long userId;

    public GetExpensesByUserIdQuery(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}

