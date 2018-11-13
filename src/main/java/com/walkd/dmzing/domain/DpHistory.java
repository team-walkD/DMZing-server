package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DpHistory extends BaseTime {
    public static final String INIT_DP = "시작 포인트";
    public static final String FIND_LETTER =  "편지 찾기";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    private Long dp;

    private String  dpType;


    @Builder
    public DpHistory(User user, Long dp, String dpType) {
        this.user = user;
        this.dp = dp;
        this.dpType = dpType;
    }
}
