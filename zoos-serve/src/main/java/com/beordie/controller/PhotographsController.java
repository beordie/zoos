package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.entity.Photographs;
import com.beordie.model.entity.User;
import com.beordie.model.factory.PhotoFactory;
import com.beordie.model.factory.UserFactory;
import com.beordie.service.IPhotographsService;
import com.beordie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
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
@RequestMapping("/photographs")
public class PhotographsController {
    @Autowired
    private IPhotographsService photographsService;

    @AccessAnnotation
    @PostMapping("insert")
    public Result createPhotographs(@RequestBody Photographs photographs) {
        photographs.setShootingTime(LocalDateTime.now());
        boolean save = photographsService.save(photographs);
        return save ? new Result<>() : Result.failed();
    }

    @GetMapping("list")
    public Result<List<Photographs>> selectPhotographs(@RequestParam("offset") Integer offset,
                                                       @RequestParam("limit") Integer limit,
                                                       HttpServletRequest request) {
        String userId = request.getHeader("userId");
        Page queryPages = PhotoFactory.buildQueryPages(offset == null ? 0 : offset, limit == null ? 10 : limit);
        List<Photographs> photographs = photographsService.selectPhotographs(queryPages, userId);
        return new Result<>(photographs);
    }

    @AccessAnnotation
    @PostMapping("delete")
    public Result deletePhotographs(@RequestParam("photoId") Integer photoId) {
        QueryWrapper<Photographs> queryWrapper = PhotoFactory.buildQueryByPhotoId(photoId);
        boolean remove = photographsService.remove(queryWrapper);
        return remove ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @PostMapping("update")
    public Result updatePhotographs(@RequestBody Photographs photographs) {
        boolean update = photographsService.updateById(photographs);
        return update ? new Result<>() : Result.failed();
    }

    @GetMapping("total")
    public Result selectTotal() {
        long total = photographsService.count();
        return new Result(total);
    }
}
