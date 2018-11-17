package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {
    List<MissionHistory> findAllByPurchasedCoursesByUser_CourseAndPurchasedCoursesByUser_User(Course course, User user);

    MissionHistory findTopByPurchasedCoursesByUserOrderByIdDesc(PurchasedCourseByUser purchasedCourseByUser);

    Boolean existsByPlaceAndPurchasedCoursesByUser(Place place,PurchasedCourseByUser purchasedCourseByUser);
}
