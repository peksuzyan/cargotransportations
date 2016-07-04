package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.service.interfaces.DriverService;
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
import static com.tsystems.cargotransportations.constants.ParamConstants.*;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(DRIVER_DIR)
@Controller
public class DriverController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    DriverService driverService;

    /**
     * Gets requests to show a entities list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Driver> drivers = driverService.getAll();
        uiModel.addAttribute(DRIVERS_PARAM, drivers);
        return DRIVER_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by number.
     * @param number entity number
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.GET)
    public String editForm(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Driver driver = driverService.getByNumber(number);
        uiModel.addAttribute(DRIVER_PARAM, driver);
        return DRIVER_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param driver entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.POST)
    public String edit(@ModelAttribute(DRIVER_PARAM) Driver driver, Model uiModel) {
        uiModel.asMap().clear();
        driverService.update(driver);
        return DRIVER_REDIRECT_PATH + driver.getNumber();
    }

    /**
     * Gets requests to delete specified entity by number.
     * @param number number
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = NUMBER_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Driver cargo = driverService.getByNumber(number);
        driverService.delete(cargo);
        uiModel.asMap().clear();
        return DRIVER_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Driver driver = new Driver();
        uiModel.addAttribute(CARGO_PARAM, driver);
        return DRIVER_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param driver driver
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(CARGO_PARAM) Driver driver, Model uiModel) {
        uiModel.asMap().clear();
        driverService.create(driver);
        return CARGO_REDIRECT_PATH + driver.getNumber();
    }
}
