package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedCourseByUserRepository extends JpaRepository<PurchasedCourseByUser, Long> {
    Long countByCourse_Type(Type type);
}
