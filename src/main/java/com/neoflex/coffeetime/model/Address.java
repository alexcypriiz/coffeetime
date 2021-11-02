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

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String first_name;

    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 30, message = "Фамилия должна содержать от 2 до 30 символов")
    private String last_name;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Укажите действительную почту")
    private String email;

    public Address() {
    }
}
