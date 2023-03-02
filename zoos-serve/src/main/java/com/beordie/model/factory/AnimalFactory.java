package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.Animal;
import com.beordie.model.entity.User;

import java.time.LocalDateTime;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 14:53
 * @User beordie
 */
public class AnimalFactory {
    public static QueryWrapper<Animal> buildQueryByAnimalId(int animalId) {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>();
        wrapper.eq("id",animalId);
        return wrapper;
    }

    public static QueryWrapper<Animal> buildQueryByAnimalClassfy(Animal animal) {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>();
        if (animal.getClassSpecies() != null) {
            wrapper.eq("classSpecies", animal.getClassSpecies());
        }
        if (animal.getSubclass() != null) {
            wrapper.eq("subclass", animal.getSubclass());
        }
        if (animal.getOrder() != null) {
            wrapper.eq("order", animal.getOrder());
        }
        if (animal.getFamily() != null) {
            wrapper.eq("family", animal.getFamily());
        }
        if (animal.getGenus() != null) {
            wrapper.eq("genus", animal.getGenus());
        }
        if (animal.getSpecies() != null) {
            wrapper.eq("species", animal.getSpecies());
        }
        if (animal.getChineseName() != null) {
            wrapper.like("chineseName", animal.getChineseName());
        } else {
            wrapper.isNotNull("chineseName");
        }
        return wrapper;
    }

    public static Animal buildDeleteAnimal(int animalId) {
        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setDeleted(1);
        animal.setUtime(LocalDateTime.now());
        return animal;
    }

    public static QueryWrapper<Animal> buildRecommendAnimals() {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend", 1);
        return wrapper;
    }

    public static Page buildQueryPages(int offset, int limit) {
        Page<User> page = new Page<>(offset,limit);
        return page;
    }
}
