package com.neoflex.coffeetime.dao;

import com.neoflex.coffeetime.model.Address;

import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
public interface InterfaceAddressDAO {
    void save(Address address);
    void update(Address address, int id);
    void delete(int id);
    void getById(int id);
    List<Address> getAll();
}
