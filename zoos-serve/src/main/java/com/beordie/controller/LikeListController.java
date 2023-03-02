package com.beordie.controller;

import com.beordie.common.Result;
import com.beordie.model.entity.LikeList;
import com.beordie.model.factory.PhotoFactory;
import com.beordie.service.ILikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Controller
@RequestMapping("/mammalian/likeList")
public class LikeListController {
    @Autowired
    private ILikeListService likeListService;

    @PostMapping("like")
    public Result liked(@RequestParam("photoId") Integer photoId,
                        HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if (userId == null) {
            return Result.ACCESS;
        }
        LikeList like = PhotoFactory.buildLike(photoId, Integer.parseInt(userId));
        boolean save = likeListService.save(like);
        return save ? new Result<>() : Result.failed();
    }
}
