package com.walkd.dmzing.domain;

import lombok.Getter;

@Getter
public enum Type {
    DATE("데이트하기 좋은 코스"),
    HISTORY("역사기행하기 좋은 코스"),
    ADVENTURE("자연탐방하기 좋은 코스");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }
}
