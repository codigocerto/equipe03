package com.codigocerto.backend.domain.enums;

import lombok.Getter;

@Getter
public enum Disponibilidade {
    
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite"),
    FIM_SEMANA("Fim de semana"),
    TOTAL_DISPONIBILIDADE("Total disponibilidade");

    private String status;

    private Disponibilidade(String status) {
        this.status = status;
    }
}