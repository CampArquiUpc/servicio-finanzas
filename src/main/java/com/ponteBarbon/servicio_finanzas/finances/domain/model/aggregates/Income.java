package com.ponteBarbon.servicio_finanzas.finances.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "incomes")
@Getter
@Setter
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    @Temporal(TemporalType.DATE)
    private Date dateOfIncome;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
