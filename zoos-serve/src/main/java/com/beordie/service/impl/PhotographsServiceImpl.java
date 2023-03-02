package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.mapper.FavoritesMapper;
import com.beordie.mapper.LikeListMapper;
import com.beordie.model.entity.Favorites;
import com.beordie.model.entity.LikeList;
import com.beordie.model.entity.Photographs;
import com.beordie.mapper.PhotographsMapper;
import com.beordie.model.factory.PhotoFactory;
import com.beordie.service.IPhotographsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class PhotographsServiceImpl extends ServiceImpl<PhotographsMapper, Photographs> implements IPhotographsService {
    private LikeListMapper likeListMapper;

    @Override
    public List<Photographs> selectPhotographs(Page queryPages, String userId) {
        List<Photographs> photographs = super.page(queryPages).getRecords();
        Map<Integer, Integer> likes = new HashMap<>();
        if (userId != null) {
            int id = Integer.parseInt(userId);
            QueryWrapper<LikeList> byUserId = PhotoFactory.buildQueryByUserId(id);
            List<LikeList> likeLists = likeListMapper.selectList(byUserId);
            likeLists.forEach(like->{
                likes.put(like.getPid(), like.getUid());
            });
        }
        photographs.stream().forEach(photograph->{
            photograph.setLike(likes.get(photograph.getId()));
        });
        return photographs;
    }
}
