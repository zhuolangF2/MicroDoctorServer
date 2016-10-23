package com.zhuolang.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hzg on 2016/10/13.
 */
@Entity
@Table(name="send_tab")
public class Send {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sendId;
    @Column(name = "userId",length = 11)
    private int userId;
    @Column(name = "sendContent")
    private String sendContent;
    @Column(name="sendTime")
    private Date sendTime;

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Send{" +
                "sendId=" + sendId +
                ", userId=" + userId +
                ", sendContent='" + sendContent + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }
}
