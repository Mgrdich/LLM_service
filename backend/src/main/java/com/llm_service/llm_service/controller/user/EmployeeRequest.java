package com.llm_service.llm_service.controller.user;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Value
@Jacksonized
@SuperBuilder
public class EmployeeRequest extends UserRequest {
    String contact;
    BigDecimal salary;
}
