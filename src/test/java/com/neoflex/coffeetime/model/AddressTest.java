package com.neoflex.coffeetime.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Alexey Podlubnyy on 25.12.2021
 */
class AddressTest {

    @Test
    void getAddress_id() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        assertEquals(0, address.getAddress_id());

    }

    @Test
    void setAddress_id() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        address.setAddress_id(2);
        assertEquals(2,address.getAddress_id());
    }

    @Test
    void getFirst_name() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        assertEquals("Alex",address.getFirst_name());
    }

    @Test
    void setFirst_name() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        address.setFirst_name("Ivan");
        assertEquals("Ivan",address.getFirst_name());
    }

    @Test
    void getLast_name() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        assertEquals("Podlubnyy", address.getLast_name());
    }

    @Test
    void setLast_name() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        address.setLast_name("Ivanov");
        assertEquals("Ivanov", address.getLast_name());
    }

    @Test
    void getEmail() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        assertEquals("alexey.podlubnyy@yandex.ru", address.getEmail());
    }

    @Test
    void setEmail() {
        Address address = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        address.setEmail("podlubnyy@yandex.ru");
        assertEquals("podlubnyy@yandex.ru", address.getEmail());
    }
}