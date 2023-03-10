package com.beordie.model.request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/8 10:59
 * @User beordie
 */
@Data
public class AdminUser {
    private String username;
    private String password;
    private String code;
    private String email;
}
