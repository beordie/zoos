package com.beordie.controller;

import com.beordie.common.Result;
import com.beordie.model.entity.LikeList;
import com.beordie.model.factory.PhotoFactory;
import com.beordie.model.request.UserLike;
import com.beordie.service.ILikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/like")
public class LikeListController {
    @Autowired
    private ILikeListService likeListService;

    @PostMapping("")
    public Result liked(@RequestBody UserLike userLike,
                        HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if (userId == null) {
            return Result.ACCESS;
        }
        int uid = Integer.parseInt(userId);
        boolean like = likeListService.like(uid, userLike.getPid(), userLike.getIsLike());
        return like ? new Result<>() : Result.failed();
    }
}
