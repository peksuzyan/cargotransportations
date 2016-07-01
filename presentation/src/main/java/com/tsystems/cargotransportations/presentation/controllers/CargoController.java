package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/admin/cargoes")
@Controller
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Cargo> cargoes = cargoService.getAllCargoes();
        uiModel.addAttribute("cargoes", cargoes);
        return "admin/cargoes/list"; // pseudo-page, resolver will append '/' and '.jsp'
    }

    @RequestMapping(value = "/{number}", params = "edit", method = RequestMethod.GET)
    public String editForm(@PathVariable("number") int number, Model uiModel) {
        Cargo cargo = cargoService.getByNumber(number);
        uiModel.addAttribute("cargo", cargo);
        return "admin/cargoes/edit"; // pseudo-page, resolver will append '/' and '.jsp'
    }

    @RequestMapping(value = "/{number}", params = "edit", method = RequestMethod.POST)
    public String edit(Cargo cargo, Model uiModel) {
        uiModel.asMap().clear();
        cargoService.save(cargo); // save method will be created later
        return "redirect:/admin/cargoes/" + cargo.getNumber(); // pseudo-page
    }

    @RequestMapping(params = "add", method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Cargo cargo = new Cargo();
        uiModel.addAttribute("cargo", cargo);
        return "admin/cargoes/add"; // pseudo-page, resolver will append '/' and '.jsp'
    }

    @RequestMapping(params = "edit", method = RequestMethod.POST)
    public String add(Cargo cargo, Model uiModel) {
        uiModel.asMap().clear();
        cargoService.save(cargo); // save method will be created later
        return "redirect:/admin/cargoes/" + cargo.getNumber(); // pseudo-page
    }

    @RequestMapping(value = "/{number}", params = "delete", method = RequestMethod.POST)
    public String delete(Cargo cargo, Model uiModel) {
        uiModel.asMap().clear();
        cargoService.delete(cargo); // delete method will be created later
        return "redirect:/admin/cargoes"; // pseudo-page
    }
}
