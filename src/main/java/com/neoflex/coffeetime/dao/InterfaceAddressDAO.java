package com.neoflex.coffeetime.dao;

import com.neoflex.coffeetime.model.Address;

import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
public interface InterfaceAddressDAO {
    void insert(Address address);
    void update(int id, Address address);
    void delete(int id);
    void getById(int id);
    List<Address> getAll();
}
