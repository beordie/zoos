package com.beordie.mammalian.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author mao
 * @createTime 2023/3/1 16:30
 * @Description: MybatisPlus逆向生成
 */
public class MybatisPlusUtils {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mammalian?serverTimezone=GMT%2B8&characterEncoding=utf-8&userSSL=false", "root", "123456")
                .globalConfig(builder -> {//全局配置
                    builder.author("coffeemao") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {//包配置
                    builder.parent("com.beordie") // 设置父包名
                            .moduleName("mammalian") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/src/main/resources/mybatis/mapper"));// 设置mapperXml生成路径

                })
                .strategyConfig(builder -> {//策略配置
                    builder.addInclude("user"); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
