package com.application.eurder.security;

import com.application.eurder.exceptions.RoleNotValidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum Role {
    CUSTOMER(new ArrayList()),
    ADMIN(new ArrayList<>(Arrays.asList(Feature.CREATE_ITEM)));
    private final List<Feature> featureList;

    Role(List<Feature> featureList) throws RoleNotValidException {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
