package com.ponteBarbon.servicio_finanzas.finances.domain.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.User;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetUserByIdQuery;
import java.util.Optional;

public interface UserService {
    Long handle(CreateUserCommand command);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> findByEmail(String email);
}
