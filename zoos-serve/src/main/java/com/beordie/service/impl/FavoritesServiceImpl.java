package com.beordie.service.impl;

import com.beordie.model.entity.Favorites;
import com.beordie.mapper.FavoritesMapper;
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

}
