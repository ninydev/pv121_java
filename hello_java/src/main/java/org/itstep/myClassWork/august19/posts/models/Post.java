package org.itstep.myClassWork.august19.posts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String title;
    private String slug;

    Category category;
    ArrayList<Tag> tags = new ArrayList<>();
}
