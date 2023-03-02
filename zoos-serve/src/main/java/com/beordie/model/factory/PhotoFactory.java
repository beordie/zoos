package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.LikeList;
import com.beordie.model.entity.Photographs;
import com.beordie.model.entity.User;

import java.time.LocalDateTime;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 8:28
 * @User beordie
 */
public class PhotoFactory {
    public static LikeList buildLike(int photoId, int userId) {
        LikeList likeList = new LikeList();
        likeList.setPid(photoId);
        likeList.setUid(userId);
        return likeList;
    }

    public static QueryWrapper<LikeList> buildQueryByUserId(int userId) {
        QueryWrapper<LikeList> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",userId);
        return wrapper;
    }

    public static QueryWrapper<Photographs> buildQueryByPhotoId(int photoId) {
        QueryWrapper<Photographs> wrapper = new QueryWrapper<>();
        wrapper.eq("id",photoId);
        return wrapper;
    }

    public static Page buildQueryPages(int offset, int limit) {
        Page<User> page = new Page<>(offset,limit);
        return page;
    }
}
