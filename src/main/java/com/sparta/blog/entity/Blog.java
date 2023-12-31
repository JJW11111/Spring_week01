package com.sparta.blog.entity;

import com.sparta.blog.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "blog") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Blog extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "password", nullable = false)
    private String password;

    public Blog(BlogRequestDto blogRequestDto){
        this.title = blogRequestDto.getTitle();
        this.username = blogRequestDto.getUsername();
        this.contents = blogRequestDto.getContents();
        this.password = blogRequestDto.getPassword();
    }

    public void update(BlogRequestDto blogRequestDto) {
        this.title = blogRequestDto.getTitle();
        this.username = blogRequestDto.getUsername();
        this.contents = blogRequestDto.getContents();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
