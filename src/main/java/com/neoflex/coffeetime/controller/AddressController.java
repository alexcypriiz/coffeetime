package com.neoflex.coffeetime.controller;

import com.neoflex.coffeetime.dao.AddressDAO;
import com.neoflex.coffeetime.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Alexey Podlubnyy on 21.10.2021
 */
@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressDAO addrDAO;
    @Autowired
    public AddressController(AddressDAO addrDAO) {
        this.addrDAO = addrDAO;
    }

    @GetMapping()
    public String getAddress(Model model) {
            List<Address> listAddress = addrDAO.getAll();
            model.addAttribute("address", listAddress);
            return "address";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("address") Address address) {
        return "create";
    }

    @PostMapping()
    public String insert(@ModelAttribute("address") @Valid Address address,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/create";
        }
        addrDAO.insert(address);
        return "redirect:/address";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("address", addrDAO.getById(id));
        return "/update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("address") @Valid Address address, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/update";
        }
        addrDAO.update(id, address);
        return "redirect:/address";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        addrDAO.delete(id);
        return "redirect:/address";
    }
}
