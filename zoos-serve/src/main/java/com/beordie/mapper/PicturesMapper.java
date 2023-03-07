package com.beordie.mapper;

import com.beordie.model.entity.Pictures;
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
public interface PicturesMapper extends BaseMapper<Pictures> {
    public List<Pictures> selectPictureByAnimalBatch(@Param("animals") List<Integer> animalIds);
}
