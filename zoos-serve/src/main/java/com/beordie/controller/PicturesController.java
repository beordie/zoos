package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.Result;
import com.beordie.model.entity.Pictures;
import com.beordie.model.entity.User;
import com.beordie.model.factory.PicturesFactory;
import com.beordie.model.factory.UserFactory;
import com.beordie.model.request.AnimalPicture;
import com.beordie.service.IPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/pictures")
public class PicturesController {
    @Autowired
    private IPicturesService picturesService;

    @GetMapping("rotated")
    public Result<List<Pictures>> getRotated() {
        QueryWrapper<Pictures> picturesQueryWrapper = PicturesFactory.buildQueryRotated();
        List<Pictures> pictures = picturesService.list(picturesQueryWrapper).subList(0, 4);
        return new Result(pictures);
    }

    @GetMapping("list")
    public Result<List<Pictures>> selectPictures(@RequestParam("offset") Integer offset,
                                 @RequestParam("limit") Integer limit) {
        Page queryPages = UserFactory.buildQueryPages(offset == null ? 1 : offset, limit == null ? 10 : limit);
        List<Pictures> pictures = picturesService.page(queryPages).getRecords();
        return new Result(pictures);
    }

    @GetMapping("count")
    public Result<Long> selectPicturesTotal() {
        long count = picturesService.count();
        return new Result(count);
    }

    @PostMapping("update")
    public Result updateRotated(@RequestParam("pictureId") Integer pictureId,
                                @RequestParam("rotated") Integer rotated) {
        Pictures pictures = PicturesFactory.buildUpdateRotated(pictureId, rotated);
        boolean update = picturesService.updateById(pictures);
        return update ? new Result<>() : Result.failed();
    }

    @PostMapping("update/small")
    public Result updateAnimalPicture(@RequestBody AnimalPicture animalPicture) {
        boolean update = picturesService.updateAnimalPicture(animalPicture);
        return update ? new Result<>() : Result.failed();
    }
}
