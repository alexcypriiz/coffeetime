package com.neoflex.coffeetime.model;

//import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
//@Data
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

    public Address(int address_id, @NotEmpty(message = "Имя не должно быть пустым") @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов") String first_name, @NotEmpty(message = "Фамилия не должна быть пустой") @Size(min = 2, max = 30, message = "Фамилия должна содержать от 2 до 30 символов") String last_name, @NotEmpty(message = "Email не должен быть пустым") @Email(message = "Укажите действительную почту") String email) {
        this.address_id = address_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
