package com.beordie.model.entity;

import java.io.Serializable;
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
@ApiModel(value = "Pictures对象", description = "")
public class Pictures implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片主键")
    private Integer id;

    @ApiModelProperty("图片地址")
    private String pictureAddress;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否主图 0代表主图，1不是主图")
    private Integer mainPicture;

    @ApiModelProperty("是否轮播，0 代表轮播，1代表不轮播")
    private String rotated;

    @ApiModelProperty("动物id")
    private Integer aid;

    @Override
    public String toString() {
        return "Pictures{" +
            "id=" + id +
            ", pictureAddress=" + pictureAddress +
            ", name=" + name +
            ", description=" + description +
            ", mainPicture=" + mainPicture +
            ", rotated=" + rotated +
            ", aid=" + aid +
        "}";
    }
}
