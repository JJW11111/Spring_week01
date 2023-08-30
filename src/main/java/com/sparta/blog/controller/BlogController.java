package com.sparta.blog.controller;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/posts")
    public List<BlogResponseDto> getBlogs(){
        return blogService.getBlogs();
    }

    @GetMapping("/post/{id}")
    public BlogResponseDto getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    @PostMapping("/post")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto blogRequestDto){
        return blogService.createBlog(blogRequestDto);
    }

    @PutMapping("/post/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto) {
        return blogService.updateBlog(id, blogRequestDto);
    }

    @DeleteMapping("/post/{id}")
    public void deleteBlog(@PathVariable Long id, @RequestParam String password){
        blogService.deleteBlog(id, password);
    }
}
