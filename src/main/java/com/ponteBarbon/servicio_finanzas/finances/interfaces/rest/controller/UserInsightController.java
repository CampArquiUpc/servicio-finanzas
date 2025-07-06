package com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.controller;

import com.ponteBarbon.servicio_finanzas.finances.application.service.UserInsightServiceImpl;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.commands.CreateUserInsightCommand;
import com.ponteBarbon.servicio_finanzas.finances.domain.model.queries.GetUserInsightByUserIdQuery;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.resource.UserInsightResource;
import com.ponteBarbon.servicio_finanzas.finances.interfaces.rest.transform.UserInsightResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-insights")
public class UserInsightController {
    private final UserInsightServiceImpl userInsightService;
    private final UserInsightResourceAssembler assembler;

    @Autowired
    public UserInsightController(UserInsightServiceImpl userInsightService, UserInsightResourceAssembler assembler) {
        this.userInsightService = userInsightService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<Long> createUserInsight(@RequestBody UserInsightResource resource) {
        CreateUserInsightCommand command = assembler.toCommand(resource);
        Long id = userInsightService.handle(command);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInsightResource> getUserInsight(@PathVariable Long userId) {
        return userInsightService.handle(new GetUserInsightByUserIdQuery(userId))
                .map(assembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

