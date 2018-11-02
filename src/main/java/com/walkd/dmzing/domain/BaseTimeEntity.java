package com.walkd.dmzing.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//해당 클래스만으로 인스턴스 생성되지 않도록 abstract로 선언
public abstract class BaseTimeEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public static Long nowToDate() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(9))).getTime();
    }

    public static Date nowAfterDaysToDate(Long days) {
        return Date.from(LocalDateTime.now().plusDays(days).toInstant(ZoneOffset.ofHours(9)));
    }
}
