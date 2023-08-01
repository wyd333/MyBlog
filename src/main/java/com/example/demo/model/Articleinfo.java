package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 12569
 * Date: 2023-08-01
 * Time: 11:51
 */
public class Articleinfo implements Serializable {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private int uid;
    private int rcount; //阅读量
    private int state;  //状态码
}
