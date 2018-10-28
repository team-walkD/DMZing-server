package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    boolean existsByImgUrl(String imgUrl);
}
