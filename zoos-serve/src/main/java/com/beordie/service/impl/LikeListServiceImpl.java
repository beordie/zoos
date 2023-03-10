package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Favorites;
import com.beordie.model.entity.LikeList;
import com.beordie.mapper.LikeListMapper;
import com.beordie.service.ILikeListService;
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
public class LikeListServiceImpl extends ServiceImpl<LikeListMapper, LikeList> implements ILikeListService {

    @Override
    public boolean like(int uid, int pid, int isLike) {
        if (isLike == 0) {
            QueryWrapper<LikeList> likeListQueryWrapper = buildQueryLikeList(uid, pid);
            return super.remove(likeListQueryWrapper);
        }
        LikeList likeList = buildLikeList(uid, pid);
        return super.save(likeList);
    }

    private QueryWrapper<LikeList> buildQueryLikeList(int uid, int pid) {
        QueryWrapper<LikeList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("uid", uid);
        return queryWrapper;
    }

    private LikeList buildLikeList(int uid, int pid) {
        LikeList likeList = new LikeList();
        likeList.setPid(pid);
        likeList.setUid(uid);
        return likeList;
    }
}
