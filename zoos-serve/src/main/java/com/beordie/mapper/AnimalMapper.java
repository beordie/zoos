package com.beordie.mapper;

import com.beordie.model.Data;
import com.beordie.model.entity.Animal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beordie.model.request.AnimalKind;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    public List<Data> getClassSpeciesOrderRanking();

    public List<Data> getOrderFamilyRanking(String order);

    public List<Data> getFamilyGenusRanking(String family);

    public List<Data> getClassSpeciesFamilyRanking();

    public List<Data> getOrderGenusRanking();

    public List<Animal> getKindClassSpecies(@Param("offset") int offset, @Param("limit") int limit);

    public List<Animal> getKindSubclass(@Param("animal") AnimalKind animal, @Param("offset") int offset, @Param("limit") int limit);

    public List<Animal> getKindOrder(@Param("animal") AnimalKind animal, @Param("offset") int offset, @Param("limit") int limit);

    public List<Animal> getKindFamily(@Param("animal") AnimalKind animal, @Param("offset") int offset, @Param("limit") int limit);

    public List<Animal> getKindGenus(@Param("animal") AnimalKind animal, @Param("offset") int offset, @Param("limit") int limit);

    public long getCountClassSpecies();

    public long getCountSubclass(@Param("animal") AnimalKind animal);

    public long getCountOrder(@Param("animal") AnimalKind animal);

    public long getCountFamily(@Param("animal") AnimalKind animal);

    public long getCountGenus(@Param("animal") AnimalKind animal);
}
