package com.walkd.dmzing.domain;

import lombok.Getter;

@Getter
public enum Type {
    DATE("데이트"),
    HISTORY("역사기행"),
    ADVENTURE("탐험");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }
}
