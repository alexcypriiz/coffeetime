package com.neoflex.coffeetime.dao;

import com.neoflex.coffeetime.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void update(int id, Address updateAddress) {
        jdbcTemplate.update("UPDATE ADDRESS SET first_name=?, last_name=?, email=? WHERE id=?",updateAddress.getFirst_name(),
                updateAddress.getLast_name(), updateAddress.getEmail(), id);
    }

    @Override
    public void insert(Address address) {
        jdbcTemplate.update("INSERT INTO ADDRESS VALUES(?, ?, ?, ?)", address.getAddress_id(), address.getFirst_name(),
                address.getLast_name(), address.getEmail());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ADDRESS WHERE id=?", id);
    }

    @Override
    public void getById(int id) {

    }
}