package com.ponteBarbon.servicio_finanzas.finances.application.service;

import com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates.UserInsight;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserInsightCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetUserInsightByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.domain.service.UserInsightService;
import com.ponteBarbon.servicio_finanzas.finances.infrastructure.persistance.JPA.UserInsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInsightServiceImpl implements UserInsightService {
    private final UserInsightRepository userInsightRepository;

    @Autowired
    public UserInsightServiceImpl(UserInsightRepository userInsightRepository) {
        this.userInsightRepository = userInsightRepository;
    }

    @Override
    public Long handle(CreateUserInsightCommand command) {
        UserInsight insight = new UserInsight();
        insight.setId(command.userId());
        insight.setInsightData(command.insightData());
        return userInsightRepository.save(insight).getId();
    }

    @Override
    public Optional<UserInsight> handle(GetUserInsightByUserIdQuery query) {
        return userInsightRepository.findByUser_Id(query.userId());
    }
}

