package com.beordie.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Data
@ApiModel(value = "Animal对象", description = "")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("纲种")
    private String classSpecies;

    @ApiModelProperty("亚纲")
    private String subclass;

    @ApiModelProperty("目")
    private String orders;

    @ApiModelProperty("科")
    private String family;

    @ApiModelProperty("属")
    private String genus;

    @ApiModelProperty("种")
    private String species;

    @ApiModelProperty("主图")
    private String upperGenusClassification;

    @ApiModelProperty("保护等级")
    private String protectionClass;

    @ApiModelProperty("是否推荐，0表示推荐，1表示不推荐")
    private Integer recommend;

    @ApiModelProperty("分布范围（坐标）")
    private String distributionRange;

    @ApiModelProperty("生活习性")
    private String life;

    @ApiModelProperty("形态特征")
    private String morphologicalCharacteristics;

    @ApiModelProperty("物种学史")
    private String historySpeciology;

    @ApiModelProperty("拉丁文名")
    private String latinName;

    @ApiModelProperty("中文名")
    private String chineseName;

    @ApiModelProperty("详细介绍")
    private String details;

    @ApiModelProperty("录入时间（创建时间）")
    private LocalDateTime ctime;

    @ApiModelProperty("修改时间")
    private LocalDateTime utime;

    @ApiModelProperty("逻辑删除")
    private Integer deleted;

    @TableField(select = false, exist = false)
    private List<String> smallPicture;

    @TableField(select = false, exist = false)
    @ApiModelProperty(required = false)
    private Integer favorite;

    @Override
    public String toString() {
        return "Animal{" +
            "id=" + id +
            ", classSpecies=" + classSpecies +
            ", subclass=" + subclass;
    }
}
