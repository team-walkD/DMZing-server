package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
