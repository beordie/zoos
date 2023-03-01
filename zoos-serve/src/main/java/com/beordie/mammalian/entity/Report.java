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
@ApiModel(value = "Report对象", description = "")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("举报事件")
    private String reportedEvent;

    @ApiModelProperty("举报时间")
    private LocalDateTime reportingTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getReportedEvent() {
        return reportedEvent;
    }

    public void setReportedEvent(String reportedEvent) {
        this.reportedEvent = reportedEvent;
    }
    public LocalDateTime getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(LocalDateTime reportingTime) {
        this.reportingTime = reportingTime;
    }

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
