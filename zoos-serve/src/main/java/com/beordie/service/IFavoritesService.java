package com.beordie.service;

import com.beordie.model.entity.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface IFavoritesService extends IService<Favorites> {
    public boolean favorites(int uid, int aid, int isFavorites);
}
