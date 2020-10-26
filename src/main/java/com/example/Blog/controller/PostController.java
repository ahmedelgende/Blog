package com.example.Blog.controller;

import com.example.Blog.Dto.PostDto;
import com.example.Blog.Dto.UserDto;
import com.example.Blog.entity.Post;
import com.example.Blog.entity.User;
import com.example.Blog.repository.PostRepository;
import com.example.Blog.repository.UserRepository;
import com.example.Blog.service.PostService;
import com.example.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private UserService userService;
    private PostService postService;

    public PostController() {
    }

    @Autowired
    public PostController(UserService userService, PostService postService, UserRepository userRepository, PostRepository postRepository) {
        this.userService = userService;
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
        postService.savePost(postDto);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<List<PostDto>>(postService.getPostsList(), HttpStatus.OK);
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(String keyword) {
        Optional<List<PostDto>> matchingPostsOptional = Optional.ofNullable(postService.searchPosts(keyword));
        if(!matchingPostsOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(matchingPostsOptional.get());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return new ResponseEntity<String>("post updated ", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Integer id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

}
