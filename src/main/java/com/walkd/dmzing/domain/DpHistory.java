package com.walkd.dmzing.domain;

import javax.persistence.*;

@Entity
public class DpHistory extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    private Long dp;

    //todo 타입이 정해지면 enum으로 수정
    private String type;

}
