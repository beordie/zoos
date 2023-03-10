package com.beordie.controller;

import com.beordie.common.Result;
import com.beordie.model.request.UserFavorite;
import com.beordie.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    private IFavoritesService favoritesService;

    @PostMapping("")
    public Result favorites(@RequestBody UserFavorite userFavorite,
                            HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if (userId == null) {
            return Result.ACCESS;
        }
        int uid = Integer.parseInt(userId);
        boolean favorites = favoritesService.favorites(uid, userFavorite.getAid(), userFavorite.getIsFavorite());
        return favorites ? new Result<>() : Result.failed();
    }
}
