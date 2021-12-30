package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.model.Address;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Alexey Podlubnyy on 27.12.2021
 */

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressDAO addrDAO;

    private List<Address> addressList;

    @BeforeEach
    void setUp() {
        this.addressList = new ArrayList<>();
        this.addressList.add(new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru"));
        this.addressList.add(new Address(1, "Alex", "Cypriiz", "alexcypriizy@yandex.ru"));
        this.addressList.add(new Address(2, "Anton", "Ivanov", "anton.ivanov@yandex.ru"));
    }

    @Test
    void getAddress() throws Exception {
        when(addrDAO.getAll()).thenReturn(addressList);

        mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("address"))
                .andExpect(MockMvcResultMatchers.model().attribute("address", Matchers.hasSize(3)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void insert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/address")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void edit() throws Exception {
        when(addrDAO.getById(0)).thenReturn(addressList.get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/address/update/{id}", 0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("/update"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/address/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/address/delete/{id}", 1))
                .andExpect(redirectedUrl("/address"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}