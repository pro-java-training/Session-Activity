package com.codve;

import lombok.Data;

import java.io.Serializable;
import java.net.InetAddress;

//用于存储用户的访问记录
@Data
public class PageVisit implements Serializable {
    private long enteredTimestamp; // 访问进入时间戳
    private Long leftTimestamp; // 访问离开时间戳
    private String request; // 请求字符串
    private InetAddress ipAddress; //  ip地址

}
