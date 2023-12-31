package org.itstep.myClassWork.august19.posts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String title;
    private String slug;

    ArrayList<Post> posts = new ArrayList<>();

    @Override
    public String toString(){
        return "\n\t Category: {title=" + this.title + " }";
    }
}
