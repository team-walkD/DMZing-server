package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
