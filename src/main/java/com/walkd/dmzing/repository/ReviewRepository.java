package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findTop30ByIdLessThanOrderByIdDesc(Long id);

    List<Review> findTop30ByOrderByIdDesc();

    boolean existsByThumbnailUrl(String imageUrl);
}
