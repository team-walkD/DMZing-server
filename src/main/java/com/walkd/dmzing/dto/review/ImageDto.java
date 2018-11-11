package com.walkd.dmzing.dto.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    @ApiModelProperty(example = "dmzing.png")
   private String image;
}
