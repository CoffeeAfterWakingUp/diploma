package com.example.diplomaprojectgeneticcode.repo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * For sorting purposes of data
 *
 * @author Olzhas Syrbek
 */
@Slf4j
public class SortDetails {

    private SortDetails() {
    }

    private static Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public static List<Sort.Order> getOrders(String[] sort) {
        log.info("Sort: {}", Arrays.toString(sort));
        if (sort == null || sort.length == 0) {
            return Collections.emptyList();
        }
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        log.info("Orders: {}", orders);
        return orders;
    }
}
