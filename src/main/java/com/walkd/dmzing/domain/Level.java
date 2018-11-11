package com.walkd.dmzing.domain;

import lombok.Getter;

@Getter
public enum Level {
    LOW("하"),
    MIDDLE("중"),
    HIGH("상");

    private String levelName;

    Level(String levelName) {
        this.levelName = levelName;
    }
}
