package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;
    private Long reviewId;
    private ReportType reportType;
    private String content;

    @Builder
    public Report(Long reviewId,User user, ReportType reportType, String content) {
        this.reviewId = reviewId;
        this.user = user;
        this.reportType = reportType;
        this.content = content;
    }
}
