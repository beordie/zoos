package com.beordie.model.request;

import lombok.Data;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/6 19:48
 * @User beordie
 */
@Data
public class AnimalZoo {
    private String classSpecies;

    private String subclass;

    private String orders;

    private String family;

    private String genus;

    private String chineseName;

    private Integer offset;

    private Integer limit;
}
