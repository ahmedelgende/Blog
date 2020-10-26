package com.example.Blog.repository;

import com.example.Blog.entity.Post;
import com.example.Blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
