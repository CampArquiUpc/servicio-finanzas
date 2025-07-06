package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loyalty_programs")
@Getter
@Setter
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer points;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
