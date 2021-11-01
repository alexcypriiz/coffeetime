package com.neoflex.coffeetime.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Data
@Component
public class Address {

    private int address_id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "First name should not be between 2 and 30 characters")
    private String first_name;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Last name should not be between 2 and 30 characters")
    private String last_name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public Address() {
    }
}
