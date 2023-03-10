package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.Data;
import com.beordie.model.entity.Animal;
import com.beordie.model.factory.AnimalFactory;
import com.beordie.model.factory.UserFactory;
import com.beordie.model.request.AnimalKind;
import com.beordie.model.request.AnimalZoo;
import com.beordie.service.IAnimalService;
import com.beordie.service.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@RestController
@RequestMapping("animal")
public class AnimalController {
    @Autowired
    private IAnimalService animalService;

    @AccessAnnotation
    @PostMapping("create")
    public Result createAnimal(@RequestBody Animal animal) {
        animal.setCtime(LocalDateTime.now());
        boolean save = animalService.save(animal);
        return save ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @PostMapping("delete")
    public Result deleteAnimal(@RequestParam("animalId") Integer animalId) {
        Animal animal = AnimalFactory.buildDeleteAnimal(animalId);
        boolean remove = animalService.removeById(animal);
        return remove ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @PostMapping("update")
    public Result updateAnimal(@RequestBody Animal animal) {
        animal.setUtime(LocalDateTime.now());
        boolean remove = animalService.updateById(animal);
        return remove ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @PostMapping("update/recommend")
    public Result updateAnimal(@RequestParam("animalId") Integer animalId,
                               @RequestParam("recommend") Integer recommend) {
        Animal animal = AnimalFactory.buildRecommendAnimal(animalId, recommend);
        boolean remove = animalService.updateById(animal);
        return remove ? new Result<>() : Result.failed();
    }

    @PostMapping("select")
    public Result<List<Animal>> selectAnimals(@RequestBody AnimalZoo animal,
                                              HttpServletRequest request) {
        String userId = request.getHeader("userId");
        List<Animal> animals = animalService.selectAnimals(animal, userId);
        return new Result<>(animals);
    }

    @AccessAnnotation
    @PostMapping("count")
    public Result<Long> selectAnimalsCount(@RequestBody AnimalZoo animal) {
        QueryWrapper<Animal> animalQueryWrapper = AnimalFactory.buildQueryByAnimalClassfy(animal);
        long count = animalService.count(animalQueryWrapper);
        return new Result<>(count);
    }

    @GetMapping("select/{animalId}")
    public Result<Animal> selectAnimal(@PathVariable("animalId") Integer animalId,
                                       HttpServletRequest request) {
        String userId = request.getHeader("userId");
        QueryWrapper<Animal> animalQueryWrapper = AnimalFactory.buildQueryByAnimalId(animalId);
        Animal animal = animalService.getAnimal(animalQueryWrapper, userId);
        return new Result<>(animal);
    }

    @GetMapping("select/recommend")
    public Result<List<Animal>> selectRecommendAnimals(HttpServletRequest request) {
        String username = request.getHeader("username");
        List<Integer> ids = animalService.getRecommendAnimals(username);
        List<Animal> animals = animalService.listByIds(ids);
        return new Result<>(animals);
    }

    @GetMapping("select/random")
    public Result<List<Animal>> selectRandomAnimals() {
        Page queryPages = AnimalFactory.buildQueryPages(0, 6);
        List<Animal> animals = animalService.page(queryPages).getRecords();
        return new Result<>(animals);
    }

    @GetMapping("select/classSpecies")
    public Result<List<String>> selectClassSpecies() {
        List<String> classSpecies = animalService.getClassSpecies();
        return new Result<>(classSpecies);
    }

    @GetMapping("select/subclass")
    public Result<List<String>> selectSubclass(@RequestParam("classSpecies") String classSpecies) {
        List<String> subclass = animalService.getSubclass(classSpecies);
        return new Result<>(subclass);
    }

    @GetMapping("select/order")
    public Result<List<String>> selectOrder(@RequestParam("subclass") String subclass) {
        List<String> order = animalService.getOrder(subclass);
        return new Result<>(order);
    }

    @GetMapping("select/family")
    public Result<List<String>> selectFamily(@RequestParam("order") String order) {
        List<String> family = animalService.getFamily(order);
        return new Result<>(family);
    }

    @GetMapping("select/genus")
    public Result<List<String>> selectGenus(@RequestParam("family") String family) {
        List<String> genus = animalService.getGenus(family);
        return new Result<>(genus);
    }

    @GetMapping("select/kind/classSpecies")
    public Result<List<Animal>> selectKindClassSpecies(@RequestParam("offset") Integer offset,
                                                       @RequestParam("limit") Integer limit) {
        List<Animal> animals = animalService.getKindClassSpecies(offset - 1, limit);
        return new Result<>(animals);
    }

    @PostMapping("select/kind/subclass")
    public Result<List<Animal>> selectKindSubclass(@RequestBody AnimalKind animal) {
        List<Animal> animals = animalService.getKindSubclass(animal, animal.getOffset() - 1, animal.getLimit());
        return new Result<>(animals);
    }

    @PostMapping("select/kind/order")
    public Result<List<Animal>> selectKindOrder(@RequestBody AnimalKind animal) {
        List<Animal> animals = animalService.getKindOrder(animal, animal.getOffset() - 1, animal.getLimit());
        return new Result<>(animals);
    }

    @PostMapping("select/kind/family")
    public Result<List<Animal>> selectKindFamily(@RequestBody AnimalKind animal) {
        List<Animal> animals = animalService.getKindFamily(animal, animal.getOffset() - 1, animal.getLimit());
        return new Result<>(animals);
    }

    @PostMapping("select/kind/genus")
    public Result<List<Animal>> selectKindGenus(@RequestBody AnimalKind animal) {
        List<Animal> animals = animalService.getKindGenus(animal, animal.getOffset() - 1, animal.getLimit());
        return new Result<>(animals);
    }

    @GetMapping("select/count/classSpecies")
    public Result<Long> selectCountClassSpecies() {
        long count = animalService.getCountClassSpecies();
        return new Result<>(count);
    }

    @PostMapping("select/count/subclass")
    public Result<Long> selectCountSubclass(@RequestBody AnimalKind animal) {
        long count = animalService.getCountSubclass(animal);
        return new Result<>(count);
    }

    @PostMapping("select/count/order")
    public Result<Long> selectCountOrder(@RequestBody AnimalKind animal) {
        long count = animalService.getCountOrder(animal);
        return new Result<>(count);
    }

    @PostMapping("select/count/family")
    public Result<Long> selectCountFamily(@RequestBody AnimalKind animal) {
        long count = animalService.getCountFamily(animal);
        return new Result<>(count);
    }

    @PostMapping("select/count/genus")
    public Result<Long> selectCountGenus(@RequestBody AnimalKind animal) {
        long count = animalService.getCountGenus(animal);
        return new Result<>(count);
    }

    @GetMapping("data/kinds")
    public Result<List<Data>> selectClassSpeciesOrderRanking() {
        List<Data> classSpeciesOrderRanking = animalService.getClassSpeciesOrderRanking();
        return new Result<>(classSpeciesOrderRanking);
    }

    @GetMapping("data/family")
    public Result<List<Data>> selectOrderFamilyRanking(@RequestParam("order") String order) {
        List<Data> classSpeciesOrderRanking = animalService.getOrderFamilyRanking(order);
        return new Result<>(classSpeciesOrderRanking);
    }

    @GetMapping("data/genus")
    public Result<List<Data>> selectFamilyGenusRanking(@RequestParam("family") String family) {
        List<Data> classSpeciesOrderRanking = animalService.getFamilyGenusRanking(family);
        return new Result<>(classSpeciesOrderRanking);
    }

    @GetMapping("data/family/max")
    public Result<Map<String, List>> selectClassSpeciesFamilyRanking() {
        Map<String, List> classSpeciesOrderRanking = animalService.getClassSpeciesFamilyRanking();
        return new Result<>(classSpeciesOrderRanking);
    }

    @GetMapping("data/genus/max")
    public Result<Map<String, List>> selectOrderGenusRanking() {
        Map<String, List> classSpeciesOrderRanking = animalService.getOrderGenusRanking();
        return new Result<>(classSpeciesOrderRanking);
    }
}
