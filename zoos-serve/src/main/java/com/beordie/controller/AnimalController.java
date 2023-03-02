package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.entity.Animal;
import com.beordie.model.factory.AnimalFactory;
import com.beordie.model.factory.UserFactory;
import com.beordie.service.IAnimalService;
import com.beordie.service.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Controller
@RequestMapping("/mammalian/animal")
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
        boolean remove = animalService.updateById(animal);
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
    @GetMapping("select")
    public Result<List<Animal>> selectAnimals(@RequestBody Animal animal,
                                              @RequestParam("offset") Integer offset,
                                              @RequestParam("limit") Integer limit) {
        QueryWrapper<Animal> animalQueryWrapper = AnimalFactory.buildQueryByAnimalClassfy(animal);
        Page queryPages = AnimalFactory.buildQueryPages(offset == null ? 0 : offset, limit == null ? 10 : limit);
        List<Animal> animals = animalService.page(queryPages, animalQueryWrapper).getRecords();
        return new Result<>(animals);
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
        List<String> family = animalService.getOrder(order);
        return new Result<>(family);
    }

    @GetMapping("select/genus")
    public Result<List<String>> selectGenus(@RequestParam("family") String family) {
        List<String> genus = animalService.getOrder(family);
        return new Result<>(genus);
    }
}
