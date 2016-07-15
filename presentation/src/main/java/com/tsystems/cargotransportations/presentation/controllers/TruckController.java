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


}
