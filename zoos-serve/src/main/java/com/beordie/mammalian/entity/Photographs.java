package com.beordie.mammalian.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }
    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }
    public LocalDateTime getShootingTime() {
        return shootingTime;
    }

    public void setShootingTime(LocalDateTime shootingTime) {
        this.shootingTime = shootingTime;
    }
    public String getShootingLocation() {
        return shootingLocation;
    }

    public void setShootingLocation(String shootingLocation) {
        this.shootingLocation = shootingLocation;
    }

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
