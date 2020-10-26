package com.example.Blog.service;

import com.example.Blog.Dto.PostDto;
import com.example.Blog.Dto.UserDto;
import com.example.Blog.entity.Post;
import com.example.Blog.entity.User;
import com.example.Blog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<PostDto> getPostsList() {
        return postRepository.findAll().stream().map(p -> {
            PostDto postDto = new PostDto(p.getPost_Id(), p.getContent(), p.getUser());
            return postDto;
        }).collect(Collectors.toList());
    }

    public PostDto getPostById(Integer postId) {
        return postRepository.findById(postId).map(p -> {
            PostDto postDto = new PostDto(p.getPost_Id(), p.getContent(), p.getUser());
            return postDto;
        }).orElse(null);
    }

    public void savePost(PostDto postDto) {
        //Post post = new Post();
        //post.setContent(postDto.getContent());
        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);

        postRepository.saveAndFlush(post);
        post.setPost_Id(post.getPost_Id());
    }

    public void updatePost(PostDto postDto) {
        //Post post = new Post(postDto.getPost_Id(), postDto.getContent(), postDto.getUser());

        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);
        postRepository.save(post);
    }

    public void deletePost(Integer id) {
        PostDto postDto = getPostById(id);

        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);
        postRepository.delete(post);
    }

    public List<PostDto> searchPosts(String keyword) {
        List<PostDto> matchingPosts = new ArrayList<>();
        List<PostDto> postsList = getPostsList();
        for (PostDto p : postsList) {
            if(p.getContent().contains(keyword)) {
                matchingPosts.add(p);
            }
        }
        return matchingPosts;
    }
}
