package com.tsystems.cargotransportations.presentation.grids;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.entity.Driver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Util class in order to create container with data to remote client.
 */
public final class OrderDetailsUtil {

    /**
     * Default constructor.
     */
    private OrderDetailsUtil() {}

    /**
     * Builds from drivers list a map representation.
     * @param drivers drivers list
     * @return drivers map
     */
    public static Map<String, String> buildDriversMap(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return Collections.emptyMap();
        return drivers.stream().collect(
                Collectors.toMap(
                        Driver::getEmail,
                        Driver::getFullName));
    }

    /**
     * Builds from cargoes list a map representation.
     * @param cargoes cargoes list
     * @return cargoes map
     */
    public static Map<Integer, String> buildCargoesMap(List<Cargo> cargoes) {
        if (cargoes == null || cargoes.isEmpty()) return Collections.emptyMap();
        return cargoes.stream().collect(
                Collectors.toMap(
                        Cargo::getId,
                        Cargo::getArrivalCity));
    }
}
