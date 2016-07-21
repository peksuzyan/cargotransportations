package com.tsystems.cargotransportations.presentation.grids;

import com.tsystems.cargotransportations.service.interfaces.GenericService;

/**
 * Util class in order to organize container with data to grid.
 */
public final class GridUtil {

    /**
     * Builds data grid by given parameters.
     * @param genericService service instance
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @param <T> entity class
     * @return data container with entities
     */
    public static <T> Grid<T> buildGrid(GenericService<T> genericService,
                                        int page, int records, String sortBy, String sortTo) {
        Grid<T> dataGrid = new Grid<>();
        dataGrid.setData(genericService.getAllByRange(page, records, sortBy, sortTo));
        dataGrid.setTotalRecords(genericService.getTotalCount());
        dataGrid.setCurrentPage(page);
        int totalPages =
                dataGrid.getTotalRecords() / records
                        + (dataGrid.getTotalRecords() % records != 0 ? 1 : 0);
        dataGrid.setTotalPages(totalPages);
        return dataGrid;
    }

}
