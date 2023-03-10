package com.beordie.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("like_list")
@ApiModel(value = "LikeList对象", description = "")
public class LikeList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer uid;

    @ApiModelProperty("摄影id")
    private Integer pid;

    @Override
    public String toString() {
        return "LikeList{" +
            "uid=" + uid +
            ", pid=" + pid +
        "}";
    }
}
