package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.MissionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {
}
