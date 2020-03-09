package com.eddi.jsonModel;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaSet {
    public List<Criteria> criteria = new ArrayList<>();

    @Override
    public String toString() {
        return "[" + String.join(", ", criteria.stream().map(Criteria::toString).collect(toList())) + "]";
    }
}
