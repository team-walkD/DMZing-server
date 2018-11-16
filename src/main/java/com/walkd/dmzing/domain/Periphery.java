package com.walkd.dmzing.domain;

import com.walkd.dmzing.dto.course.place.PeripheryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Periphery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageUrl;
    private Long contentTypeId;

    @Builder
    public Periphery(String title, String imageUrl,Long contentTypeId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.contentTypeId = contentTypeId;
    }

    public PeripheryDto toPeripheryDto(){
        return PeripheryDto.builder()
                .firstimage(imageUrl)
                .title(title)
                .contenttypeid(contentTypeId)
                .build();
    }
}
