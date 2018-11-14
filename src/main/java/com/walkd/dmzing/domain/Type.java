package com.walkd.dmzing.domain;

import lombok.Getter;

@Getter
public enum Type {
    DATE("데이트 맵"),
    HISTORY("역사기행 맵"),
    ADVENTURE("탐험 맵");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }
}
