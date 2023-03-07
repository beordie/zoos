package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.mapper.PicturesMapper;
import com.beordie.model.Data;
import com.beordie.model.entity.Animal;
import com.beordie.mapper.AnimalMapper;
import com.beordie.model.entity.Pictures;
import com.beordie.model.factory.AnimalFactory;
import com.beordie.model.factory.PicturesFactory;
import com.beordie.model.request.AnimalKind;
import com.beordie.model.request.AnimalZoo;
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

    @Autowired
    private PicturesMapper picturesMapper;

    private Cache<String, List<Integer>> recommendAnimals = null;

    @Override
    public List<Animal> selectAnimals(AnimalZoo animal) {
        QueryWrapper<Animal> animalQueryWrapper = AnimalFactory.buildQueryByAnimalClassfy(animal);
        Page queryPages = AnimalFactory.buildQueryPages(animal.getOffset() == null ? 0 : animal.getOffset(),
                animal.getLimit() == null ? 10 : animal.getLimit());
        List<Animal> animals = super.page(queryPages, animalQueryWrapper).getRecords();
        List<Integer> animalIds = new ArrayList<>();
        animals.forEach(zoo->{
            animalIds.add(zoo.getId());
        });
        List<Pictures> pictures = picturesMapper.selectPictureByAnimalBatch(animalIds);
        Map<Integer, List> map = new HashMap<>();
        pictures.forEach(picture->{
            List<String> address = Arrays.asList(picture.getPictureAddress().split(","));
            map.put(picture.getAid(), address);
        });
        animals.forEach(zoo->{
            zoo.setSmallPicture(map.get(zoo.getId()));
        });
        return animals;
    }

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

    @Override
    public List<Data> getClassSpeciesOrderRanking() {
        List<Data> datas = animalMapper.getClassSpeciesOrderRanking();
        return datas;
    }

    @Override
    public List<Data> getOrderFamilyRanking(String order) {
        List<Data> datas = animalMapper.getOrderFamilyRanking(order);
        return datas;
    }

    @Override
    public List<Data> getFamilyGenusRanking(String family) {
        List<Data> datas = animalMapper.getFamilyGenusRanking(family);
        return datas;
    }

    @Override
    public Map<String, List> getClassSpeciesFamilyRanking() {
        List<Data> datas = animalMapper.getClassSpeciesFamilyRanking();
        return translateData(datas);
    }

    @Override
    public Map<String, List> getOrderGenusRanking() {
        List<Data> datas = animalMapper.getOrderGenusRanking();
        return translateData(datas);
    }

    @Override
    public Animal getAnimal(QueryWrapper<Animal> animalQueryWrapper) {
        Animal animal = super.getOne(animalQueryWrapper);
        QueryWrapper<Pictures> picturesQueryWrapper = PicturesFactory.buildQuerySmall(animal.getId());
        List<Pictures> pictures = picturesMapper.selectList(picturesQueryWrapper);
        List smallPicture = new ArrayList();
        pictures.forEach(picture -> {
            smallPicture.add(picture.getPictureAddress());
        });
        animal.setSmallPicture(smallPicture);
        return animal;
    }

    @Override
    public List<Animal> getKindClassSpecies(int offset, int limit) {
        return animalMapper.getKindClassSpecies(offset, limit);
    }

    @Override
    public List<Animal> getKindSubclass(AnimalKind animal, int offset, int limit) {
        return animalMapper.getKindSubclass(animal, offset, limit);
    }

    @Override
    public List<Animal> getKindOrder(AnimalKind animal, int offset, int limit) {
        return animalMapper.getKindOrder(animal, offset, limit);
    }

    @Override
    public List<Animal> getKindFamily(AnimalKind animal, int offset, int limit) {
        return animalMapper.getKindFamily(animal, offset, limit);
    }

    @Override
    public List<Animal> getKindGenus(AnimalKind animal, int offset, int limit) {
        return animalMapper.getKindGenus(animal, offset, limit);
    }

    @Override
    public long getCountClassSpecies() {
        return animalMapper.getCountClassSpecies();
    }

    @Override
    public long getCountSubclass(AnimalKind animal) {
        return animalMapper.getCountSubclass(animal);
    }

    @Override
    public long getCountOrder(AnimalKind animal) {
        return animalMapper.getCountOrder(animal);
    }

    @Override
    public long getCountFamily(AnimalKind animal) {
        return animalMapper.getCountFamily(animal);
    }

    @Override
    public long getCountGenus(AnimalKind animal) {
        return animalMapper.getCountGenus(animal);
    }

    private Map<String, List> translateData(List<Data> datas) {
        List name = new ArrayList(), data = new ArrayList();
        datas.forEach((d)->{
            name.add(d.getName());
            data.add(d.getValue());
        });
        HashMap<String, List> map = new HashMap<>();
        map.put("name", name);
        map.put("data", data);
        return map;
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
