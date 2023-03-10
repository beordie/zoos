package com.beordie.model.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value = "Favorites对象", description = "")
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer uid;

    @ApiModelProperty("动物id")
    private Integer aid;

    @Override
    public String toString() {
        return "Favorites{" +
            "uid=" + uid +
            ", aid=" + aid +
        "}";
    }
}
