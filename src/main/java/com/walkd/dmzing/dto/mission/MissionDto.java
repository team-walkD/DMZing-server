package com.walkd.dmzing.dto.mission;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MissionDto {
    @ApiModelProperty(example = "1", position = 1)
    private Long cid;

    @ApiModelProperty(example = "3", position = 2)
    private Long pid;

    @ApiModelProperty(example = "38.1879930099", position = 3)
    private Double latitude;

    @ApiModelProperty(example = "127.2182962522", position = 4)
    private Double longitude;
}
