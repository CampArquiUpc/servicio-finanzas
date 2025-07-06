package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_insights")
@Getter
@Setter
public class UserInsight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String insightData;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
