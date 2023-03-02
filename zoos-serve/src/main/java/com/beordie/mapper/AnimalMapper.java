package com.beordie.mapper;

import com.beordie.model.entity.Animal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface AnimalMapper extends BaseMapper<Animal> {
    public List<String> getClassSpecies();

    public List<String> getSubclass(@Param("classSpecies") String classSpecies);

    public List<String> getOrder(String subclass);

    public List<String> getFamily(String order);

    public List<String> getGenus(String family);
}
