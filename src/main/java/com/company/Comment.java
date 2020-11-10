package com.company;

import org.joda.time.DateTime;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Comment implements IToJason {

    //region Variables
    private User user;
    private DateTime datetime;
    private String content;
    private Comment replyTo;
    //endregion

    //region Constructor

    public Comment(User user, DateTime datetime, String content, Comment replyTo) {
        this.user = user;
        this.datetime = datetime;
        this.content = content;
        this.replyTo = replyTo;
    }
    //endregion

    //region Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }
    //endregion

    //region Methods

    //endregion
}
