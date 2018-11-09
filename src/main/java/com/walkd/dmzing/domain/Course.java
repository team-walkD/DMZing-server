package com.walkd.dmzing.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Type type;

    private String mainDescription;

    private String subDescription;

    private String imageUrl;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserPickedMap> userPickedMaps;

    @Builder
    public Course(Type type, String mainDescription, String subDescription, String imageUrl){
        this.type = type;
        this.mainDescription = mainDescription;
        this.subDescription = subDescription;
        this.imageUrl = imageUrl;
    }

    public void

}
