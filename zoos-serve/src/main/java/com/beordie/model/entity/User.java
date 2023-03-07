package com.beordie.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户唯一主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("创建时间")
    private LocalDateTime ctime;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", phone=" + phone +
            ", sex=" + sex +
            ", idNumber=" + idNumber +
            ", ctime=" + ctime +
        "}";
    }
}
