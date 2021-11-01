package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Controller
public class AddressController {
    private final AddressDAO addrDAO;
    @Autowired
    public AddressController(AddressDAO addrDAO) {
        this.addrDAO = addrDAO;
    }

    @GetMapping("/address")
    public String getAddress(Model model) {
            List<Address> listAddress = addrDAO.getAll();
            model.addAttribute("address", listAddress);
            return "address";
    }
}
