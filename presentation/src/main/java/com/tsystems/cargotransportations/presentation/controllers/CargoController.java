package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.EDIT_ACTION;
import static com.tsystems.cargotransportations.constants.ParamConstants.CARGOES_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.CARGO_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.NUMBER_PARAM;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(CARGO_DIR)
@Controller
public class CargoController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    private CargoService cargoService;

    /**
     * Gets requests to show a cargoes list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Cargo> cargoes = cargoService.getAll();
        uiModel.addAttribute(CARGOES_PARAM, cargoes);
        return CARGO_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by number.
     * @param number entity number
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.GET)
    public String editForm(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Cargo cargo = cargoService.getByNumber(number);
        uiModel.addAttribute(CARGO_PARAM, cargo);
        return CARGO_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param cargo entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.POST)
    public String edit(@ModelAttribute(CARGO_PARAM) Cargo cargo, Model uiModel) {
        uiModel.asMap().clear();
        cargoService.update(cargo);
        return CARGO_REDIRECT_PATH + cargo.getNumber();
    }

    /**
     * Gets requests to show delete form with specified entity by number.
     * @param number number
     * @param uiModel UI model
     * @return path to logic page of deleting form
     */
    @RequestMapping(value = NUMBER_DIR, params = DELETE_ACTION, method = RequestMethod.GET)
    public String deleteForm(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Cargo cargo = cargoService.getByNumber(number);
        uiModel.addAttribute(CARGO_PARAM, cargo);
        return CARGO_DELETE_PATH;
    }

    /**
     * Gets requests to delete specified entity by number.
     * @param number number
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = NUMBER_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Cargo cargo = cargoService.getByNumber(number);
        cargoService.delete(cargo);
        uiModel.asMap().clear();
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Cargo cargo = new Cargo();
        uiModel.addAttribute(CARGO_PARAM, cargo);
        return CARGO_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param cargo entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(CARGO_PARAM) Cargo cargo, Model uiModel) {
        uiModel.asMap().clear();
        cargoService.create(cargo);
        return CARGO_REDIRECT_PATH + cargo.getNumber();
    }
}