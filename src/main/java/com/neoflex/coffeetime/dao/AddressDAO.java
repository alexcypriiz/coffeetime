package com.neoflex.coffeetime.dao;

import com.neoflex.coffeetime.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Component
public class AddressDAO implements InterfaceAddressDAO {
    private JdbcTemplate jdbcTemplate;

    public AddressDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> getAll() {
        return jdbcTemplate.query("SELECT * FROM ADDRESS", new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public void save(Address address) {

    }

    @Override
    public void update(Address address, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void getById(int id) {

    }
}
