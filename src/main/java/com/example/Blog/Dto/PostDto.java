package com.example.Blog.Dto;

import com.example.Blog.entity.User;

public class PostDto {
    private int post_Id;
    private String content;
    private User user;

    public PostDto() {
    }

    public PostDto(int post_Id, String content, User user) {
        this.post_Id = post_Id;
        this.content = content;
        this.user = user;
    }

    public int getPost_Id() {
        return post_Id;
    }

    public void setPost_Id(int post_Id) {
        this.post_Id = post_Id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
