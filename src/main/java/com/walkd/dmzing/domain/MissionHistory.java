package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionHistory extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Place place;

    @ManyToOne
    @JoinColumn
    private PurchasedCourseByUser purchasedCoursesByUser;

    @Builder
    public MissionHistory(Place place, PurchasedCourseByUser purchasedCourseByUser) {
        this.place = place;
        this.purchasedCoursesByUser = purchasedCourseByUser;
    }


}
