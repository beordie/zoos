package com.beordie.service;

import com.beordie.model.entity.Pictures;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beordie.model.request.AnimalPicture;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface IPicturesService extends IService<Pictures> {
    public boolean updateAnimalPicture(AnimalPicture animalPicture);
}
