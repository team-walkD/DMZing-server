package com.walkd.dmzing.util;

import com.walkd.dmzing.repository.MissionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitComponent implements ApplicationRunner {

    private final MissionHistoryRepository missionHistoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+missionHistoryRepository.findById(1l).orElseThrow(RuntimeException::new));
    }
}
