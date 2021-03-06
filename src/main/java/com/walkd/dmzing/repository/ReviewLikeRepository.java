package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Review;
import com.walkd.dmzing.domain.ReviewLike;
import com.walkd.dmzing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    ReviewLike findByReviewAndUser(Review review, User user);


}
