package com.sparta.blog;

import com.sparta.blog.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class TransactionTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("메모 생성 성공")
    void test(){
        Blog blog = new Blog();
        blog.setTitle("제목");
        blog.setUsername("Robbert");
        blog.setContents("테스트 중");

        em.persist(blog);
    }

}
