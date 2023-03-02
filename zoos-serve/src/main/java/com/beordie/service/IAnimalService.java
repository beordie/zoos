package com.beordie.service;

import com.beordie.model.entity.Animal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface IAnimalService extends IService<Animal> {
    public List<Integer> getRecommendAnimals(String username);

    public List<String> getClassSpecies();

    public List<String> getSubclass(String classSpecies);

    public List<String> getOrder(String subclass);

    public List<String> getFamily(String order);

    public List<String> getGenus(String family);
}
