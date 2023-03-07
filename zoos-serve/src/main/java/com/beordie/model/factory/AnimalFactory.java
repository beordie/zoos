package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.Animal;
import com.beordie.model.entity.User;
import com.beordie.model.request.AnimalZoo;

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

    public static QueryWrapper<Animal> buildQueryByAnimalClassfy(AnimalZoo animal) {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>();
        if (animal.getClassSpecies() != null && animal.getClassSpecies().length() > 0) {
            wrapper.eq("class_species", animal.getClassSpecies());
        }
        if (animal.getSubclass() != null && animal.getSubclass().length() > 0) {
            wrapper.eq("subclass", animal.getSubclass());
        }
        if (animal.getOrders() != null && animal.getOrders().length() > 0) {
            wrapper.eq("orders", animal.getOrders());
        }
        if (animal.getFamily() != null && animal.getFamily().length() > 0) {
            wrapper.eq("family", animal.getFamily());
        }
        if (animal.getGenus() != null && animal.getGenus().length() > 0) {
            wrapper.eq("genus", animal.getGenus());
        }
        if (animal.getChineseName() != null && animal.getChineseName().length() > 0) {
            wrapper.like("chinese_name", animal.getChineseName());
        } else {
            wrapper.isNotNull("chinese_name");
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

    public static Animal buildRecommendAnimal(int animalId, int recommend) {
        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setRecommend(recommend);
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
