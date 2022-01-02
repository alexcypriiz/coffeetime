package com.neoflex.coffeetime.dao;

import com.neoflex.coffeetime.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alexey Podlubnyy on 31.12.2021
 */
@SpringBootTest
class AddressDAOTest {

    @InjectMocks
    private AddressDAO addressDAO;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private List<Address> addressList;

    private List<String> emailList;

    @BeforeEach
    void setUp() {
        this.addressList = new ArrayList<>();
        this.addressList.add(new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru"));
        this.addressList.add(new Address(1, "Alex", "Cypriiz", "alexcypriizy@yandex.ru"));
        this.addressList.add(new Address(2, "Anton", "Ivanov", "anton.ivanov@yandex.ru"));

        this.emailList = new ArrayList<>();
        this.emailList.add("alexey.podlubnyy@yandex.ru");
        this.emailList.add("alexcypriizy@yandex.ru");
        this.emailList.add("anton.ivanov@yandex.ru");
    }

    @Test
    void getAll() {
        when(jdbcTemplate.query(anyString(),(BeanPropertyRowMapper) anyObject())).thenReturn(addressList);
        assertEquals(addressList.size(), addressDAO.getAll().size());
    }

    @Test
    void getAllEmail() {
        when(jdbcTemplate.queryForList("SELECT email FROM ADDRESS", String.class)).thenReturn(emailList);
        assertEquals(emailList.size(), addressDAO.getAllEmail().size());
    }

    @Test
    void update() {
        Address updateAddress = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");

        addressDAO.update(0, updateAddress);
        verify(jdbcTemplate).update(anyString(), eq(updateAddress.getFirst_name()), eq(updateAddress.getLast_name()), eq(updateAddress.getEmail()), eq(0));
    }

    @Test
    void insert() {
        Address updateAddress = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");

        addressDAO.insert(updateAddress);
        verify(jdbcTemplate).update(anyString(),eq(updateAddress.getAddress_id()), eq(updateAddress.getLast_name()), eq(updateAddress.getFirst_name()), eq(updateAddress.getEmail()));
    }

    @Test
    void delete() {
        addressDAO.delete(0);
        verify(jdbcTemplate).update(anyString(),eq(0));
    }

    @Test
    void getById() {
        List list = new ArrayList();
        Address getByAddress = new Address(0, "Alex", "Podlubnyy", "alexey.podlubnyy@yandex.ru");
        list.add(getByAddress);
        when(jdbcTemplate.query(anyString(),(Object[]) anyObject(), (BeanPropertyRowMapper) anyObject())).thenReturn(list);
        assertEquals(getByAddress, addressDAO.getById(0));

    }
}