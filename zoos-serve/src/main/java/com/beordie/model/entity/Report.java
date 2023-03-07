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
@ApiModel(value = "Report对象", description = "")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("举报事件")
    private String reportedEvent;

    @ApiModelProperty("举报时间")
    private LocalDateTime reportingTime;

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", name=" + name +
            ", phone=" + phone +
            ", reportedEvent=" + reportedEvent +
            ", reportingTime=" + reportingTime +
        "}";
    }
}
