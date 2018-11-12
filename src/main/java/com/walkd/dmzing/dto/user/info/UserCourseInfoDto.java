package com.walkd.dmzing.dto.user.info;

import com.walkd.dmzing.domain.Type;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserCourseInfoDto {

    private Long id;
    private Type type;
    private String title;
    private String mainDescription;
    private String subDescription;
    private String imageUrl;
    private String lineImageUrl;
    private Long price;

}
