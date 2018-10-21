package com.walkd.dmzing.domain;

import com.walkd.dmzing.util.DateUtil;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends DateUtil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    private String title;
    private String content;

    @JoinColumn(name = "post_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PostImg> postImgs;

}
