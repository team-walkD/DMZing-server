package com.walkd.dmzing.domain;

import lombok.Getter;

@Getter
public enum Type {
    DATE("데이트 맵"),
    HISTORY("역사기행 맵"),
    ADVENTURE("자연탐방 맵"),
    DMZ("DMZ탑방 맵");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }
}
