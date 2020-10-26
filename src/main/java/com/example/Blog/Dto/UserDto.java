package com.example.Blog.Dto;

import com.example.Blog.entity.Post;

import javax.persistence.OneToMany;
import java.util.Set;

public class UserDto {
    private Integer userId;

    private String name;
    private Integer age;

    private Set<Post> posts;

    public UserDto() {
    }

    public UserDto(Integer userId, String name, Integer age, Set<Post> posts) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
