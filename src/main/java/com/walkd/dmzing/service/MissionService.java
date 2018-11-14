package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.MissionHistory;
import com.walkd.dmzing.domain.PurchasedCourseByUser;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.PlaceDto;
import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundPurchaseHistoryException;
import com.walkd.dmzing.repository.MissionHistoryRepository;
import com.walkd.dmzing.repository.PhotoReviewRepository;
import com.walkd.dmzing.repository.PurchasedCourseByUserRepository;
import com.walkd.dmzing.repository.ReviewRepository;
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

    private final MissionHistoryRepository missionHistoryRepository;

    private final ReviewRepository reviewRepository;

    private final PhotoReviewRepository photoReviewRepository;

    @Transactional
    public PurchaseListAndPickCourseDto showPurchaseListAndPickCourse(String email) {
        //todo 구매내역 익셉
        List<PurchasedCourseByUser> purchasedCourseByUsers = purchasedCourseByUserRepository.findByUser_Email(email).orElseThrow(NotFoundPurchaseHistoryException::new);

        List<CourseSimpleDto> purchaseList = purchasedCourseByUsers.stream()
                .map(purchasedCourseByUser ->
                        CourseSimpleDto.builder()
                                .title(purchasedCourseByUser.getCourse().getType().getTypeName())
                                .id(purchasedCourseByUser.getCourse().getId())
                                .isPicked(purchasedCourseByUser.getIsPicked())
                                .build())
                .collect(Collectors.toList());

        PurchasedCourseByUser pickPurchasedCourse = purchasedCourseByUsers.stream()
                .filter(purchasedCourseByUser -> purchasedCourseByUser.getIsPicked())
                .collect(Collectors.toList()).get(0);

        Course course = pickPurchasedCourse.getCourse();

        MissionHistory missionHistory = missionHistoryRepository.findTopByPurchasedCoursesByUserOrderByIdDesc(pickPurchasedCourse);

        return PurchaseListAndPickCourseDto.builder().pickCourse(pickPurchasedCourse.getCourse().toCourseDetailDto(reviewRepository.countByCourse_Type(course.getType())
                + photoReviewRepository.countByCourse_Type(course.getType()), missionHistory))
                .purchaseList(purchaseList).build();
    }

    @Transactional
    public List<PlaceDto> filterSuccessPlaces(String email, Long cid) {
        PurchasedCourseByUser purchasedCourse = purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).orElseThrow(NotFoundPurchaseHistoryException::new);
        if (purchasedCourse.getIsPicked()) {
            return missionHistoryRepository.findByPurchasedCoursesByUser_Id(purchasedCourse.getId())
                    .stream()
                    .map(missionHistory -> missionHistory.getPlace().toPlaceDto())
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("픽한 코스가 아닙니다.");
        }
    }

    @Transactional
    public CourseDetailDto pickCourse(Long cid, String email) {
        List<PurchasedCourseByUser> purchasedCourseList = purchasedCourseByUserRepository.findByUser_Email(email).orElseThrow(NotFoundPurchaseHistoryException::new);
        PurchasedCourseByUser purchasedCourse = purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(cid, email).orElseThrow(NotFoundCourseException::new);

        if (!purchasedCourseList.isEmpty()) {
            purchasedCourseList
                    .stream()
                    .forEach(purchasedCourseByUser -> purchasedCourseByUser.setPicked(Boolean.FALSE));
            purchasedCourse.setPicked(true);

            Course course = purchasedCourse.getCourse();

            MissionHistory missionHistory = missionHistoryRepository.findTopByPurchasedCoursesByUserOrderByIdDesc(purchasedCourse);

            return course.toCourseDetailDto(reviewRepository.countByCourse_Type(course.getType())
                    + photoReviewRepository.countByCourse_Type(course.getType()), missionHistory);
        }
        throw new NotFoundPurchaseHistoryException();
    }
}
