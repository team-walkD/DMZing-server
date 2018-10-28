package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
