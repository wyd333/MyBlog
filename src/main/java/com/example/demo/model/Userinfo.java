package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:42
 */

// 实现序列化和反序列化，如果没有声明，在存储到redis时会报错
@Data
public class Userinfo implements Serializable {
    private int id;
    private String username;
    private String password;
    private String photo;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private int state;
}
