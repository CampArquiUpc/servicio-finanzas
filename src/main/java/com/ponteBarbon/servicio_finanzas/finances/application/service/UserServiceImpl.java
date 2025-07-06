package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.User;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetUserByIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.UserService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        User user = new User();
        user.setName(command.name());
        user.setEmail(command.email());
        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
