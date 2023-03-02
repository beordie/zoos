package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.Report;
import com.beordie.model.entity.User;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 9:01
 * @User beordie
 */
public class ReportFactory {
    public static Page buildQueryPages(int offset, int limit) {
        Page<User> page = new Page<>(offset,limit);
        return page;
    }

    public static QueryWrapper<Report> buildQueryByReportId(int reportId) {
        QueryWrapper<Report> wrapper = new QueryWrapper<>();
        wrapper.eq("id",reportId);
        return wrapper;
    }
}
