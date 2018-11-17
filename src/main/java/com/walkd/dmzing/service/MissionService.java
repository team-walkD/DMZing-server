package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import com.walkd.dmzing.dto.mission.MissionDto;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundPurchaseHistoryException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.*;
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

    private final UserRepository userRepository;

    private final DpHistoryRepository dpHistoryRepository;

    @Transactional
    public PurchaseListAndPickCourseDto showPurchaseListAndPickCourse(String email) {
        //todo 구매내역 익셉
        List<PurchasedCourseByUser> purchasedCourseByUsers = purchasedCourseByUserRepository.findByUser_Email(email).orElseThrow(NotFoundPurchaseHistoryException::new);

        List<CourseSimpleDto> purchaseList = purchasedCourseByUsers.stream()
                .map(purchasedCourseByUser -> purchasedCourseByUser.toCourseSimpleDto())
                .collect(Collectors.toList());

        PurchasedCourseByUser pickPurchasedCourse = purchasedCourseByUsers.stream()
                .filter(purchasedCourseByUser -> purchasedCourseByUser.getIsPicked())
                .collect(Collectors.toList()).get(0);

        Course course = pickPurchasedCourse.getCourse();

        MissionHistory missionHistory = missionHistoryRepository.findTopByPurchasedCoursesByUserOrderByIdDesc(pickPurchasedCourse);

        return PurchaseListAndPickCourseDto.builder().pickCourse(pickPurchasedCourse.getCourse()
                .toCourseDetailDto(reviewRepository.countByCourse_Type(course.getType())
                + photoReviewRepository.countByCourse_Type(course.getType()), missionHistory))
                .purchaseList(purchaseList).build();
    }

    @Transactional
    public List<PlaceDto> filterSuccessPlaces(String email, MissionDto missionDto) {
        User user= userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        PurchasedCourseByUser purchasedCourse = purchasedCourseByUserRepository.findByCourse_IdAndUser_Email(missionDto.getCid(), email)
                .orElseThrow(NotFoundPurchaseHistoryException::new);
        //todo 구매목록 찾고 픽된건지 보고 픽 되어있으면 장소 반환 장소와 구매아이디로 미션목록 조회 -> 없으면 위경도 조회 통과시 유저 디피 올리고 미션 저장 히스토리저장
        Place checkPlace = purchasedCourse.getPlace(missionDto.getPid());

        if(!missionHistoryRepository.existsByPlaceAndPurchasedCoursesByUser(checkPlace,purchasedCourse)){
            Long reward = checkPlace.checkSuccessed(missionDto.getLatitude(),missionDto.getLongitude());
            user.addDmzPoint(reward);
            dpHistoryRepository.save(DpHistory.builder().dpType(DpHistory.FIND_LETTER).user(user).dp(reward).build());
            List<PlaceDto> placeDtos = checkPlace.toPlaceDtos(purchasedCourse.getCourse()
                    .makePlaceList(missionHistoryRepository
                            .save(MissionHistory.builder().purchasedCourseByUser(purchasedCourse).place(checkPlace).build())));
            return checkPlace.getRemovedPlaceDtos(placeDtos);
        }
        //todo 사용자에러발생
        throw new RuntimeException();
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
