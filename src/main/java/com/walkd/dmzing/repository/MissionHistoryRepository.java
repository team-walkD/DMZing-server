package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.MissionHistory;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {
    List<MissionHistory> findByPurchasedCoursesByUser_Id(Long pcid);

    MissionHistory findAllByPurchasedCoursesByUserId(Long id);

    Optional<List<MissionHistory>> findAllByPurchasedCoursesByUser_CourseAndPurchasedCoursesByUser_User(Course course, User user);

    MissionHistory findTopByPurchasedCoursesByUserOrderByIdDesc(PurchasedCourseByUser purchasedCourseByUser);
}
