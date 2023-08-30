package com.sparta.blog.service;

import com.sparta.blog.dto.BlogRequestDto;
import com.sparta.blog.dto.BlogResponseDto;
import com.sparta.blog.entity.Blog;
import com.sparta.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<BlogResponseDto> getBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto getBlogById(Long id) {
        Blog blog = findBlog(id);
        return new BlogResponseDto(blog);
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

    public BlogResponseDto createBlog(BlogRequestDto blogRequestDto) {
        Blog blog = new Blog(blogRequestDto);

        Blog saveBlog = blogRepository.save(blog);

        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);

        return blogResponseDto;
    }

    @Transactional
    public Blog updateBlog(Long id, BlogRequestDto blogRequestDto) {
        Blog blog = findBlog(id);

        if(!blog.getPassword().equals(blogRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        blog.update(blogRequestDto);

        return blog;
    }

    @Transactional
    public void deleteBlog(Long id, String password) {
        Blog blog = findBlog(id);

        if(!blog.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        blogRepository.delete(blog);
    }
}
