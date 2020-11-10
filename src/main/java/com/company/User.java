package com.company;

import org.joda.time.DateTime;
import java.util.List;

public class User implements IToJason {

    //region Variables
    private long UserId;
    private String Username;
    private String EmailAddress;
    private DateTime joinDate;
    private List<Comment> userComments;

    //endregion

    //region Constructor
    public User(long userId, String username, String emailAddress) {
        UserId = userId;
        Username = username;
        EmailAddress = emailAddress;
        joinDate = DateTime.now();
    }
    //endregion

    //region Getter and Setter
    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public DateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(DateTime joinDate) {
        this.joinDate = joinDate;
    }
    //endregion

    //region Methods

    // Add Comment
    public void addComment(String content) {
        Comment comment = new Comment(this, DateTime.now(), content, null);
        userComments.add(comment);
    }

    public void addComment(String content, Comment replyTo) {
        Comment comment = new Comment(this, DateTime.now(), content, replyTo);
        userComments.add(comment);
    }

    //endregion
}
