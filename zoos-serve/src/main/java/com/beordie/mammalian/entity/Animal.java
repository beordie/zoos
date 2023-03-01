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
@ApiModel(value = "Animal对象", description = "")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("唯一主键")
    private Integer id;

    @ApiModelProperty("纲种")
    private String classSpecies;

    @ApiModelProperty("亚纲")
    private String subclass;

    @ApiModelProperty("目")
    private String order;

    @ApiModelProperty("科")
    private String family;

    @ApiModelProperty("属")
    private String genus;

    @ApiModelProperty("种")
    private String species;

    @ApiModelProperty("上属分类")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getClassSpecies() {
        return classSpecies;
    }

    public void setClassSpecies(String classSpecies) {
        this.classSpecies = classSpecies;
    }
    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
    public String getUpperGenusClassification() {
        return upperGenusClassification;
    }

    public void setUpperGenusClassification(String upperGenusClassification) {
        this.upperGenusClassification = upperGenusClassification;
    }
    public String getProtectionClass() {
        return protectionClass;
    }

    public void setProtectionClass(String protectionClass) {
        this.protectionClass = protectionClass;
    }
    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }
    public String getDistributionRange() {
        return distributionRange;
    }

    public void setDistributionRange(String distributionRange) {
        this.distributionRange = distributionRange;
    }
    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }
    public String getMorphologicalCharacteristics() {
        return morphologicalCharacteristics;
    }

    public void setMorphologicalCharacteristics(String morphologicalCharacteristics) {
        this.morphologicalCharacteristics = morphologicalCharacteristics;
    }
    public String getHistorySpeciology() {
        return historySpeciology;
    }

    public void setHistorySpeciology(String historySpeciology) {
        this.historySpeciology = historySpeciology;
    }
    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }
    public LocalDateTime getUtime() {
        return utime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Animal{" +
            "id=" + id +
            ", classSpecies=" + classSpecies +
            ", subclass=" + subclass +
            ", order=" + order +
            ", family=" + family +
            ", genus=" + genus +
            ", species=" + species +
            ", upperGenusClassification=" + upperGenusClassification +
            ", protectionClass=" + protectionClass +
            ", recommend=" + recommend +
            ", distributionRange=" + distributionRange +
            ", life=" + life +
            ", morphologicalCharacteristics=" + morphologicalCharacteristics +
            ", historySpeciology=" + historySpeciology +
            ", latinName=" + latinName +
            ", chineseName=" + chineseName +
            ", details=" + details +
            ", ctime=" + ctime +
            ", utime=" + utime +
            ", deleted=" + deleted +
        "}";
    }
}
