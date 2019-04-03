package com.codve;

import java.io.Serializable;
import java.net.InetAddress;

//用于存储用户的访问记录
public class PageVisit implements Serializable {
    private long enteredTimestamp; // 访问进入时间戳
    private Long leftTimestamp; // 访问离开时间戳
    private String request; // 请求字符串
    private InetAddress ipAddress; //  ip地址

    public long getEnteredTimestamp()
    {
        return enteredTimestamp;
    }

    public void setEnteredTimestamp(long enteredTimestamp)
    {
        this.enteredTimestamp = enteredTimestamp;
    }

    public Long getLeftTimestamp()
    {
        return leftTimestamp;
    }

    public void setLeftTimestamp(Long leftTimestamp)
    {
        this.leftTimestamp = leftTimestamp;
    }

    public String getRequest()
    {
        return request;
    }

    public void setRequest(String request)
    {
        this.request = request;
    }

    public InetAddress getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress)
    {
        this.ipAddress = ipAddress;
    }

}
