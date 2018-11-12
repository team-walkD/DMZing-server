package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PurchasedCourseByUserRepository extends JpaRepository<PurchasedCourseByUser, Long> {
    Long countByCourse_TypeAndIsPickedTrue(Type type);

    Boolean existsByCourse_TypeAndUser_Email(Type type, String email);

    Optional<PurchasedCourseByUser> findByCourse_IdAndUser_Email(Long cid, String email);

    List<PurchasedCourseByUser> findAllByUserId(Long uid);

}
