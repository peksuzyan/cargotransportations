package com.tsystems.cargotransportations.constants.mapping;

/**
 * Contains all logic page names that are used by Spring controllers and actual pages.
 */
public final class PresentationMapper {

    /**
     * Default private constructor.
     */
    private PresentationMapper() {}

    /**
     * Keyword for redirect to given page.
     */
    public static final String REDIRECT = "redirect:";

    /**
     * Keyword for slash symbol.
     */
    public static final String SLASH = "/";

    /**
     * Represents page when some resource isn't found.
     */
    public static final String PAGE_404 = "/404";

    /**
     * Admin directory.
     */
    public static final String ADMIN = "/admin";

    /**
     * Subdirectory of personal entity page.
     */
    public static final String ID_DIR = "/{id_number}";

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
    public static final String CARGO_DIR = ADMIN + "/cargoes";

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
    public static final String DRIVER_DIR = ADMIN + "/drivers";

    /**
     * Path to entity redirect page.
     */
    public static final String DRIVER_REDIRECT_PATH = REDIRECT + DRIVER_DIR;

    /**
     * Path to entity edit page after redirect.
     */
    public static final String DRIVER_REDIRECT_PATH_WITH = DRIVER_REDIRECT_PATH + SLASH;

    /**
     * Path to entity list page.
     */
    public static final String DRIVER_LIST_PATH = DRIVER_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String DRIVER_EDIT_PATH = DRIVER_DIR + EDIT_DIR;

    /* <---------- TRUCK ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String TRUCK_DIR = ADMIN + "/trucks";

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

    /* <---------- USER ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String USER_DIR = ADMIN + "/users";

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

    /* <---------- ROUTE ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String ROUTE_DIR = ADMIN + "/routes";

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

    /* <---------- ORDER ----------> */

    /**
     * Subdirectory of entity page.
     */
    public static final String ORDER_DIR = ADMIN + "/orders";

    /**
     * Path to entity redirect page.
     */
    public static final String ORDER_REDIRECT_PATH = REDIRECT + ORDER_DIR;

    /**
     * Path to entity edit page after redirect.
     */
    public static final String ORDER_REDIRECT_PATH_WITH = ORDER_REDIRECT_PATH + SLASH;

    /**
     * Path to entity list page.
     */
    public static final String ORDER_LIST_PATH = ORDER_DIR + LIST_DIR;

    /**
     * Path to entity edit page.
     */
    public static final String ORDER_EDIT_PATH = ORDER_DIR + EDIT_DIR;

    /**
     * Path to creating empty order.
     */
    public static final String ORDER_NEW_PATH = "/new";

    /**
     * Path to action performing over cargo within order.
     */
    public static final String ORDER_CARGO_PATH = "/cargo";

    /**
     * Path to action performing over truck within order.
     */
    public static final String ORDER_TRUCK_PATH = "/truck";

    /**
     * Path to action performing over driver within order.
     */
    public static final String ORDER_DRIVER_PATH = "/driver";

    /**
     * Path to action performing over route within order.
     */
    public static final String ORDER_ROUTE_PATH = "/route";
}
