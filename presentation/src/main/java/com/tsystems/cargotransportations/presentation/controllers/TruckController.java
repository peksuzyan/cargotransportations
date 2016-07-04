package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;
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
import static com.tsystems.cargotransportations.constants.ParamConstants.NUMBER_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.TRUCKS_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.TRUCK_PARAM;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(TRUCK_DIR)
@Controller
public class TruckController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    TruckService truckService;

    /**
     * Gets requests to show a entities list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Truck> trucks = truckService.getAll();
        uiModel.addAttribute(TRUCKS_PARAM, trucks);
        return TRUCK_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by number.
     * @param number entity number
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.GET)
    public String editForm(@PathVariable(NUMBER_PARAM) String number, Model uiModel) {
        Truck truck = truckService.getByNumber(number);
        uiModel.addAttribute(TRUCK_PARAM, truck);
        return TRUCK_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param truck entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.POST)
    public String edit(@ModelAttribute(TRUCK_PARAM) Truck truck, Model uiModel) {
        uiModel.asMap().clear();
        truckService.update(truck);
        return DRIVER_REDIRECT_PATH + truck.getNumber();
    }

    /**
     * Gets requests to delete specified entity by number.
     * @param number number
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = NUMBER_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NUMBER_PARAM) String number, Model uiModel) {
        Truck truck = truckService.getByNumber(number);
        truckService.delete(truck);
        uiModel.asMap().clear();
        return TRUCK_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Truck truck = new Truck();
        uiModel.addAttribute(TRUCK_PARAM, truck);
        return TRUCK_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param truck truck
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(TRUCK_PARAM) Truck truck, Model uiModel) {
        uiModel.asMap().clear();
        truckService.create(truck);
        return TRUCK_REDIRECT_PATH + truck.getNumber();
    }
}
