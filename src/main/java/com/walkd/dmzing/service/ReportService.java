package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.dto.report.ReportDto;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.ReportRepository;
import com.walkd.dmzing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    public void createReport(ReportDto reportDto,String email){
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        reportRepository.save(reportDto.toEntity(user));
    }
}
