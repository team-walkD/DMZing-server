package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.course.CourseDetailDto;
import com.walkd.dmzing.dto.course.CourseSimpleDto;
import com.walkd.dmzing.dto.course.PurchaseListAndPickCourseDto;
import com.walkd.dmzing.dto.course.place.PlaceDto;
import com.walkd.dmzing.dto.mission.MissionDto;
import com.walkd.dmzing.exception.AlreadySuccessedException;
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
        List<PurchasedCourseByUser> purchasedCourseByUsers = purchasedCourseByUserRepository.findByUser_Email(email)
                .orElseThrow(NotFoundPurchaseHistoryException::new);

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
        Place checkPlace = purchasedCourse.getPlace(missionDto.getPid());

        if(!missionHistoryRepository.existsByPlaceAndPurchasedCoursesByUser(checkPlace,purchasedCourse)){
            Long reward = checkPlace.checkSuccessed(missionDto.getLatitude(),missionDto.getLongitude());
            user.addDmzPoint(reward);
            List<PlaceDto> placeDtos = checkPlace.toPlaceDtos(purchasedCourse.getCourse()
                    .makePlaceList(missionHistoryRepository
                            .save(MissionHistory.builder().purchasedCourseByUser(purchasedCourse).place(checkPlace).build())));
            dpHistoryRepository.save(DpHistory.builder().dpType(DpHistory.FIND_LETTER).user(user).dp(reward).build());
            return checkPlace.getRemovedPlaceDtos(placeDtos);
        }
        throw new AlreadySuccessedException();
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
