package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Pictures;
import com.beordie.mapper.PicturesMapper;
import com.beordie.model.factory.PicturesFactory;
import com.beordie.model.request.AnimalPicture;
import com.beordie.service.IPicturesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class PicturesServiceImpl extends ServiceImpl<PicturesMapper, Pictures> implements IPicturesService {

    @Override
    @Transactional
    public boolean updateAnimalPicture(AnimalPicture animalPicture) {
        QueryWrapper<Pictures> querySmall = PicturesFactory.buildQuerySmall(animalPicture.getAnimalId());
        boolean remove = super.remove(querySmall);
        List<Pictures> pictures = PicturesFactory.buildNewAnimalPicture(animalPicture);
        boolean batch = super.saveBatch(pictures);
        return batch;
    }
}
