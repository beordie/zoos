package com.beordie.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/8 20:58
 * @User beordie
 */
@Data
public class UserFavorite {
    private Integer aid;

    private Integer isFavorite;
}
