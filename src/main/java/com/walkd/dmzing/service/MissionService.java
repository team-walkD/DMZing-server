package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.PlaceDto;
import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.repository.CourseRepository;
import com.walkd.dmzing.repository.MissionHistoryRepository;
import com.walkd.dmzing.repository.PlaceRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionService {

    private final PurchasedCourseByUserRepository purchasedCourseByUserRepository;

    private final PlaceRepository placeRepository;

    private final MissionHistoryRepository missionHistoryRepository;

    private final CourseRepository courseRepository;


    @Transactional
    public PurchaseListAndPickCourseDto showPurchaseListAndPickCourse(String email) {
        List<CourseSimpleDto> purchaseList = purchasedCourseByUserRepository.findByUser_Email(email)
                .stream()
                .map(purchasedCourseByUser ->
                        CourseSimpleDto.builder()
                                .type(purchasedCourseByUser.getCourse().getType())
                                .id(purchasedCourseByUser.getCourse().getId())
                                .isPicked(purchasedCourseByUser.getIsPicked())
                                .build())
                .collect(Collectors.toList());

        Course pickCourse = purchasedCourseByUserRepository.findByUser_EmailAndIsPickedTrue(email).getCourse();
        //todo: 디테일디티오 오버라이딩
        return PurchaseListAndPickCourseDto.builder().pickCourse(pickCourse.toCourseDetailDto())
                .purchaseList(purchaseList).build();
    }

    public List<PlaceDto> filterSuccessPlaces(String email, Long cid) {
        PurchasedCourseByUser purchasedCourse = purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).orElseThrow(NotFoundCourseException::new);
        if(purchasedCourse.getIsPicked()){
            return missionHistoryRepository.findByPurchasedCoursesByUser_Id(purchasedCourse.getId())
                    .stream()
                    .map(missionHistory -> missionHistory.getPlace().toPlaceDto())
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("픽한 코스가 아닙니다.");
        }
    }

}
