package org.kuro.recruit.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    private String fromUser;
    private String topMessage;
    private String time;

    public Message(String fromUser, String topMessage, String time) {
        this.fromUser = fromUser;
        this.topMessage = topMessage;
        this.time = time;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getTopMessage() {
        return topMessage;
    }

    public void setTopMessage(String topMessage) {
        this.topMessage = topMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static List<Message> list() {
        List<Message> list = new ArrayList<>();

        list.add(new Message("微微智能科技有限公司", "请问还在考虑新的工作机会吗？", "14:27"));
        list.add(new Message("微微智能科技有限公司", "请问还在考虑新的工作机会吗？", "14:27"));
        list.add(new Message("微微智能科技有限公司", "请问还在考虑新的工作机会吗？", "14:27"));
        list.add(new Message("微微智能科技有限公司", "请问还在考虑新的工作机会吗？", "14:27"));

        return list;
    }
}
