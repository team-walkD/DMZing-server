package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.PhotoReview;
import com.walkd.dmzing.domain.Review;
import com.walkd.dmzing.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoReviewRepository extends JpaRepository<PhotoReview,Long> {
    boolean existsByImageUrl(String imageUrl);

    List<PhotoReview> findTop30ByIdAndCourse_TypeLessThanOrderByIdDesc(Long id, Type type);

    List<PhotoReview> findTop30ByCourse_TypeOrderByIdDesc(Type type);

    Long countByCourse_Type(Type type);
}
