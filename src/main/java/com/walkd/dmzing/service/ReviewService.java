package com.walkd.dmzing.service;

import com.walkd.dmzing.domain.*;
import com.walkd.dmzing.dto.review.ReviewCountDto;
import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.dto.review.SimpleReviewDto;
import com.walkd.dmzing.exception.AlreadySaveImageException;
import com.walkd.dmzing.exception.BadImageUrlException;
import com.walkd.dmzing.exception.NotFoundCourseException;
import com.walkd.dmzing.exception.NotFoundUserException;
import com.walkd.dmzing.repository.*;
import com.walkd.dmzing.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PostImgRepository postImgRepository;

    @Autowired
    private PhotoReviewRepository photoReviewRepository;

    @Autowired
    private ReviewLikeRepository reviewLikeRepository;

    private final S3Util s3Util;

    @Transactional
    public void createReview(ReviewDto reviewDto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        Course course = courseRepository.findById(reviewDto.getCourseId()).orElseThrow(NotFoundCourseException::new);

        reviewRepository.save(reviewDto.toEntity().setUser(user).setCourse(course));
    }

    public List<SimpleReviewDto> showReviews(Long id, Type type, String email) {
        if (id == 0) {
            return reviewRepository.findTop30ByCourse_TypeOrderByIdDesc(type)
                    .stream()
                    .map(review -> review.toSimpleDto(userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new)))
                    .collect(Collectors.toList());
        }
        return reviewRepository.findTop30ByIdAndCourse_TypeLessThanOrderByIdDesc(id, type)
                .stream()
                .map(review -> review.toSimpleDto(userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new)))
                .collect(Collectors.toList());
    }

    public void removeImage(String imageUrl) {
        //todo 나중에 이미지 db 다 합쳐야한다.
        if (!reviewRepository.existsByThumbnailUrl(imageUrl) &&
                !postImgRepository.existsByImgUrl(imageUrl) &&
                !photoReviewRepository.existsByImageUrl(imageUrl)) {
            try {
                s3Util.removeFileFromS3(new StringBuilder()
                        .append(S3Util.REVIEW_DIR)
                        .append(imageUrl.substring(imageUrl.lastIndexOf("/"))).toString());
                return;
            } catch (StringIndexOutOfBoundsException e) {
                throw new BadImageUrlException();
            }
        }
        throw new AlreadySaveImageException();
    }

    public String uploadImg(MultipartFile multipartFile) throws IOException {
        return s3Util.upload(multipartFile, s3Util.REVIEW_DIR);
    }

    public ReviewDto showReview(Long rid, String email) {
        return reviewRepository.findById(rid).orElseThrow(NotFoundCourseException::new)
                .toDto(userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new));
    }

    public List<ReviewCountDto> showReviewCount() {
        //todo group by로 리펙토링 하고싶다.......
        return Arrays.stream(Type.values())
                .map(type -> ReviewCountDto.builder().typeName(type.getTypeName())
                        .conut(reviewRepository.countByCourse_Type(type) + photoReviewRepository.countByCourse_Type(type))
                        .build())
                .collect(Collectors.toList());
    }

    public Boolean createReviewLike(Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        Review review = reviewRepository.findById(id).orElseThrow(NotFoundCourseException::new);
        ReviewLike reviewLike = reviewLikeRepository.findByReviewAndUser(review, user);

        if (reviewLike == null) {
            reviewLikeRepository.save(new ReviewLike(user, review));
            return true;
        }

        reviewLikeRepository.delete(reviewLike);
        return false;
    }
}
