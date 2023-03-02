package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.entity.Report;
import com.beordie.model.entity.User;
import com.beordie.model.factory.ReportFactory;
import com.beordie.model.factory.UserFactory;
import com.beordie.service.IReportService;
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
@Controller
@RequestMapping("/mammalian/report")
public class ReportController {
    @Autowired
    private IReportService reportService;

    @PostMapping("commit")
    public Result<Report> commit(@RequestBody Report report) {
        report.setReportingTime(LocalDateTime.now());
        boolean save = reportService.save(report);
        return save ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @GetMapping("list")
    public Result<List<Report>> selectUsers(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        Page queryPages = ReportFactory.buildQueryPages(offset == null ? 0 : offset, limit == null ? 10 : limit);
        List<Report> reports = reportService.page(queryPages).getRecords();
        return new Result<>(reports);
    }

    @AccessAnnotation
    @GetMapping("delete")
    public Result deleteUser(@RequestParam("reportId") Integer reportId) {
        QueryWrapper<Report> queryWrapper = ReportFactory.buildQueryByReportId(reportId);
        boolean remove = reportService.remove(queryWrapper);
        return remove ? new Result<>() : Result.failed();
    }
}
