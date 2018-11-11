package com.walkd.dmzing.dto.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikeDto {
    @ApiModelProperty(example = "true")
    private Boolean isLike;
}
