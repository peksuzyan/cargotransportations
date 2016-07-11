package com.tsystems.cargotransportations.constants;

public class PresentationConstants {
    /**
     * Keyword for redirect to given page.
     */
    public static final String REDIRECT = "redirect:";

    /**
     * Keyword for slash symbol.
     */
    public static final String SLASH = "/";

    /**
     * Subdirectory of personal entity page.
     */
    public static final String NUMBER_DIR = "/{number}";

    /**
     * Subdirectory of personal entity page.
     */
    public static final String ID_DIR = "/{id_number}";

    /**
     * Subdirectory of personal entity page (only user).
     */
    public static final String NAME_DIR = "/{name}";

    /**
     * Subdirectory of entities list page.
     */
    public static final String LIST_DIR = "/list";

    /**
     * Subdirectory of entities list grid page.
     */
    public static final String LIST_GRID_DIR = "/listgrid";


    /**
     * Subdirectory of entity adding page.
     */
    public static final String ADD_DIR = "/add";

    /**
     * Subdirectory of entity editing page.
     */
    public static final String EDIT_DIR = "/edit";

    /**
     * Subdirectory of entity deleting page.
     */
    private static final String DELETE_DIR = "/delete";

    /* <---------- CARGO ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String CARGO_DIR = "/cargoes";

    /**
     * Path to entity redirect page.
     */
    public static final String CARGO_REDIRECT_PATH = REDIRECT + CARGO_DIR;

    /**
     * Path to entity edit page after redirect.
     */
    public static final String CARGO_REDIRECT_PATH_WITH = CARGO_REDIRECT_PATH + SLASH;

    /**
     * Path to entity list page.
     */
    public static final String CARGO_LIST_PATH = CARGO_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String CARGO_EDIT_PATH = CARGO_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String CARGO_ADD_PATH = CARGO_DIR + ADD_DIR;

    /**
     * Path to entity delete page.
     */
    public static final String CARGO_DELETE_PATH = CARGO_DIR + DELETE_DIR;

    /* <---------- DRIVER ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String DRIVER_DIR = "/drivers";

    /**
     * Path to entity redirect page.
     */
    public static final String DRIVER_REDIRECT_PATH = REDIRECT + DRIVER_DIR;

    /**
     * Path to entity list page.
     */
    public static final String DRIVER_LIST_PATH = DRIVER_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String DRIVER_EDIT_PATH = DRIVER_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String DRIVER_ADD_PATH = DRIVER_DIR + ADD_DIR;

    /* <---------- TRUCK ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String TRUCK_DIR = "/trucks";

    /**
     * Path to entity redirect page.
     */
    public static final String TRUCK_REDIRECT_PATH = REDIRECT + TRUCK_DIR;

    /**
     * Path to entity list page.
     */
    public static final String TRUCK_LIST_PATH = TRUCK_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String TRUCK_EDIT_PATH = TRUCK_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String TRUCK_ADD_PATH = TRUCK_DIR + ADD_DIR;

    /* <---------- USER ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String USER_DIR = "/users";

    /**
     * Path to entity redirect page.
     */
    public static final String USER_REDIRECT_PATH = REDIRECT + USER_DIR;

    /**
     * Path to entity list page.
     */
    public static final String USER_LIST_PATH = USER_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String USER_EDIT_PATH = USER_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String USER_ADD_PATH = USER_DIR + ADD_DIR;

    /* <---------- ROUTE ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String ROUTE_DIR = "/routes";

    /**
     * Path to entity redirect page.
     */
    public static final String ROUTE_REDIRECT_PATH = REDIRECT + ROUTE_DIR;

    /**
     * Path to entity list page.
     */
    public static final String ROUTE_LIST_PATH = ROUTE_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String ROUTE_EDIT_PATH = ROUTE_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String ROUTE_ADD_PATH = ROUTE_DIR + ADD_DIR;

    /* <---------- ORDER ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String ORDER_DIR = "/orders";

    /**
     * Path to entity redirect page.
     */
    public static final String ORDER_REDIRECT_PATH = REDIRECT + ORDER_DIR;

    /**
     * Path to entity list page.
     */
    public static final String ORDER_LIST_PATH = ORDER_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String ORDER_EDIT_PATH = ORDER_DIR + EDIT_DIR;

    /**
     * Path to entity add page.
     */
    public static final String ORDER_ADD_PATH = ORDER_DIR + ADD_DIR;
}
