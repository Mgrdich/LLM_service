package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class FullName {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
