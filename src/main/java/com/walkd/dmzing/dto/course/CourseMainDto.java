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
    private String mainDescription;
    private String subDescription;
    private String imageUrl;
    private String lineImageUrl;

    private Long pickCount;
    private Boolean isPurchased;

//    public CourseMainDto(Course entity) {
//        type = entity.getType();
//        mainDescription = entity.getMainDescription();
//        subDescription = entity.getSubDescription();
//        imageUrl = entity.getImageUrl();
//        lineImageUrl = entity.getLineImageUrl();
//    }

    // toEntity로 만들면 어떻게 하나?
    public Course toEntity() {
        return Course.builder()
                .type(type)
                .mainDescription(mainDescription)
                .subDescription(subDescription)
                .imageUrl(imageUrl)
                .lineImageUrl(lineImageUrl)
                .build();
    }

}
