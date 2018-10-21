package com.walkd.dmzing.service;

import com.walkd.dmzing.dto.review.ReviewDto;
import com.walkd.dmzing.repository.PostImgRepository;
import com.walkd.dmzing.repository.PostRepository;
import com.walkd.dmzing.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostImgRepository postImgRepository;

    @Transactional
    public void createReview(ReviewDto reviewDto) {
        reviewRepository.save(reviewDto.toEntity());
    }

}
