package com.beordie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.Data;
import com.beordie.model.entity.Animal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beordie.model.request.AnimalKind;
import com.beordie.model.request.AnimalZoo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface IAnimalService extends IService<Animal> {
    public List<Animal> selectAnimals(AnimalZoo animal, String userId);

    public List<Integer> getRecommendAnimals(String username);

    public List<String> getClassSpecies();

    public List<String> getSubclass(String classSpecies);

    public List<String> getOrder(String subclass);

    public List<String> getFamily(String order);

    public List<String> getGenus(String family);

    public List<Data> getClassSpeciesOrderRanking();

    public List<Data> getOrderFamilyRanking(String order);

    public List<Data> getFamilyGenusRanking(String family);

    public Map<String, List> getClassSpeciesFamilyRanking();

    public Map<String, List> getOrderGenusRanking();

    public Animal getAnimal(QueryWrapper<Animal> animalQueryWrapper, String userId);

    public List<Animal> getKindClassSpecies(int offset, int limit);

    public List<Animal> getKindSubclass(AnimalKind animal, int offset, int limit);

    public List<Animal> getKindOrder(AnimalKind animal, int offset, int limit);

    public List<Animal> getKindFamily(AnimalKind animal, int offset, int limit);

    public List<Animal> getKindGenus(AnimalKind animal, int offset, int limit);

    public long getCountClassSpecies();

    public long getCountSubclass(AnimalKind animal);

    public long getCountOrder(AnimalKind animal);

    public long getCountFamily(AnimalKind animal);

    public long getCountGenus(AnimalKind animal);
}
