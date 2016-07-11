package com.tsystems.cargotransportations.presentation.grids;

import java.util.List;

/**
 * Container in order to pass as JSON into response.
 * @param <T> contained entity class
 */
public class Grid<T> {

    /**
     * Total pages count by type of given entity.
     */
    private int totalPages;

    /**
     * Current page with entities of given type.
     */
    private int currentPage;

    /**
     * Total records count with given entities.
     */
    private int totalRecords;

    /**
     * A list of data with records about given type entities.
     */
    private List<T> data;

    /**
     * Gets totalPages.
     *
     * @return totalPages totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets totalPages.
     *
     * @param totalPages totalPages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Gets currentPage.
     *
     * @return currentPage currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets currentPage.
     *
     * @param currentPage currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets totalRecords.
     *
     * @return totalRecords totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * Sets totalRecords.
     *
     * @param totalRecords totalRecords
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * Gets data.
     *
     * @return data data
     */
    public List<T> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data data
     */
    public void setData(List<T> data) {
        this.data = data;
    }
}
