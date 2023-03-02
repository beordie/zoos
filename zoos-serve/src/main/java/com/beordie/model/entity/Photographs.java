package com.beordie.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "Photographs对象", description = "")
public class Photographs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("摄影作品主键")
    private Integer id;

    @ApiModelProperty("中文名")
    private String chineseName;

    @ApiModelProperty("拉丁文名")
    private String latinName;

    @ApiModelProperty("拍摄人")
    private String photographer;

    @ApiModelProperty("拍摄时间")
    private LocalDateTime shootingTime;

    @ApiModelProperty("拍摄地点")
    private String shootingLocation;

    /**
     * 是否点赞
     */
    private Integer like;

    @Override
    public String toString() {
        return "Photographs{" +
            "id=" + id +
            ", chineseName=" + chineseName +
            ", latinName=" + latinName +
            ", photographer=" + photographer +
            ", shootingTime=" + shootingTime +
            ", shootingLocation=" + shootingLocation +
        "}";
    }
}
