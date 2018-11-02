package com.walkd.dmzing.dto.user;

import io.swagger.annotations.ApiModelProperty;

public interface LoginUser {
    @ApiModelProperty(example = "example@gmail.com", position = 1)
    String getEmail();

    @ApiModelProperty(example = "qwer1234@@", position = 2)
    String getPassword();
}
