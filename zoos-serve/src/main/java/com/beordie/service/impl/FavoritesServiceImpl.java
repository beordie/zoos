package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Favorites;
import com.beordie.mapper.FavoritesMapper;
import com.beordie.model.entity.User;
import com.beordie.service.IFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {

    @Override
    public boolean favorites(int uid, int aid, int isFavorites) {
        if (isFavorites == 0) {
            QueryWrapper<Favorites> favoritesQueryWrapper = buildQueryFavorites(uid, aid);
            return super.remove(favoritesQueryWrapper);
        }
        Favorites favorites = buildFavorites(uid, aid);
        return super.save(favorites);
    }

    private QueryWrapper<Favorites> buildQueryFavorites(int uid, int aid) {
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aid", aid);
        queryWrapper.eq("uid", uid);
        return queryWrapper;
    }

    private Favorites buildFavorites(int uid, int aid) {
        Favorites favorites = new Favorites();
        favorites.setAid(aid);
        favorites.setUid(uid);
        return favorites;
    }
}
