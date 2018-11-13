package com.walkd.dmzing.dto.course;

import com.walkd.dmzing.domain.Course;
import com.walkd.dmzing.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class CourseMainDto {

    private Long id;
    private Type type;
    private String title;
    private String mainDescription;
    private String subDescription;
    private String imageUrl;
    private String lineImageUrl;
    private Long price;

    private Long pickCount;
    private Boolean isPurchased;

    // toEntity로 만들면 어떻게 하나?
    public Course toEntity() {
        return Course.builder()
                .type(type)
                .title(title)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .imageUrl(imageUrl)
                .lineImageUrl(lineImageUrl)
                .price(price)
                .build();
    }

}
