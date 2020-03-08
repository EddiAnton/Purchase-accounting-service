package com.eddi;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSet {
    List<Criteria> criteria = new ArrayList<>();

    @Override
    public String toString() {
        return "[" + String.join(", ", criteria.stream().map(Criteria::toString).collect(toList())) + "]";
    }
}
