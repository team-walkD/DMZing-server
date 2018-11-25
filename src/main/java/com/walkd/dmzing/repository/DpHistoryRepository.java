package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.DpHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DpHistoryRepository extends JpaRepository<DpHistory, Long> {

    List<DpHistory> findAllByUser_Email(String email);
}
