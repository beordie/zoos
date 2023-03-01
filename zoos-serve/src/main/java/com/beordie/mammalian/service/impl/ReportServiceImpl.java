package com.beordie.mammalian.service.impl;

import com.beordie.mammalian.entity.Report;
import com.beordie.mammalian.mapper.ReportMapper;
import com.beordie.mammalian.service.IReportService;
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
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

}
