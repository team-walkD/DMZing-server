package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Review;
import com.walkd.dmzing.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findTop30ByIdAndCourse_TypeLessThanOrderByIdDesc(Long id, Type type);

    List<Review> findTop30ByCourse_TypeOrderByIdDesc(Type type);

    boolean existsByThumbnailUrl(String imageUrl);

    Long countByCourse_Type(Type type);

    Long countById(Long id);
}
