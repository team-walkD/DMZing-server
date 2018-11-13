package com.walkd.dmzing.dto.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCountDto {

    @ApiModelProperty(example = "history", position = 1)
    private String typeName;

    @ApiModelProperty(example = "312", position = 2)
    private Long conut;

    @ApiModelProperty(example = "dmzing.png", position = 3)
    private String imageUrl;
}
