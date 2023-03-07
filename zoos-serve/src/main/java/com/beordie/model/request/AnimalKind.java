package com.beordie.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/6 14:48
 * @User beordie
 */
public class AnimalKind {
    private String classSpecies;

    private String subclass;

    private String orders;

    private String family;

    private String genus;

    private String species;

    private Integer offset;

    private Integer limit;

    public String getClassSpecies() {
        return classSpecies;
    }

    public void setClassSpecies(String classSpecies) {
        this.classSpecies = classSpecies;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
