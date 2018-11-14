package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.MissionHistory;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {
    List<MissionHistory> findByPurchasedCoursesByUser_Id(Long pcid);

    MissionHistory findAllByPurchasedCoursesByUserId(Long id);

    MissionHistory findTopByPurchasedCoursesByUserOrderByIdDesc(PurchasedCourseByUser purchasedCourseByUser);
}
