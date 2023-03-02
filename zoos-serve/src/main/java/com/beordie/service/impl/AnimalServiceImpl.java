package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Animal;
import com.beordie.mapper.AnimalMapper;
import com.beordie.model.factory.AnimalFactory;
import com.beordie.service.IAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {
    @Autowired
    private AnimalMapper animalMapper;

    private Cache<String, List<Integer>> recommendAnimals = null;

    @Override
    public List<Integer> getRecommendAnimals(String username) {
        if (username == null) {
            username = "beordie.sky";
        }
        List<Integer> animals = recommendAnimals.get(username, new Function<String, List<Integer>>() {
            @Override
            public List<Integer> apply(String username) {
                return allotAnimals(username);
            }
        });
        return animals;
    }

    @Override
    public List<String> getClassSpecies() {
        return animalMapper.getClassSpecies();
    }

    @Override
    public List<String> getSubclass(String classSpecies) {
        return animalMapper.getSubclass(classSpecies);
    }

    @Override
    public List<String> getOrder(String subclass) {
        return animalMapper.getOrder(subclass);
    }

    @Override
    public List<String> getFamily(String order) {
        return animalMapper.getFamily(order);
    }

    @Override
    public List<String> getGenus(String family) {
        return animalMapper.getGenus(family);
    }

    public AnimalServiceImpl() {
        recommendAnimals = Caffeine.newBuilder()
                .initialCapacity(1000)
                .maximumSize(10000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

    private List<Integer> allotAnimals(String username) {
        List<Integer> allAnimals = recommendAnimals.get("beordie.cloud", new Function<String, List<Integer>>() {
            @Override
            public List<Integer> apply(String username) {
                QueryWrapper<Animal> animalQueryWrapper = AnimalFactory.buildRecommendAnimals();
                List<Animal> animals = list(animalQueryWrapper);
                List<Integer> ids = new ArrayList<>();
                animals.forEach(animal->{
                    ids.add(animal.getId());
                });
                return ids;
            }
        });
        Random random = new Random();
        List<Integer> recommendAnimals = new ArrayList<>();
        Set<Integer> tempAnimals = new HashSet<>();
        while (tempAnimals.size() < 4) {
            tempAnimals.add(random.nextInt(allAnimals.size()));
        }
        tempAnimals.stream().forEach(id->{
            recommendAnimals.add(id);
        });
        return recommendAnimals;
    }
}
