package org.itstep.myClassWork.august19.posts;

import org.itstep.myClassWork.august19.posts.models.Category;
import org.itstep.myClassWork.august19.posts.models.Post;
import org.itstep.myClassWork.august19.posts.models.Tag;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class August19Posts implements Runnable
{
    @Override
    public void run() {
        System.out.println("Работа с постами");
        seed();
    }

    ArrayList<Tag> tags = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    //ArrayList<Post> posts = new ArrayList<>();
    ArrayDeque<Post> posts = new ArrayDeque<>();

    private void seed(){
        Category cNews = new Category();
        cNews.setTitle("News");
        cNews.setSlug("/news");

        Category cPromo = new Category();
        cPromo.setTitle("Promo");
        cPromo.setSlug("/promo");

        categories.add(cNews);
        categories.add(cPromo);

        Tag tOdesa = new Tag();
        tOdesa.setTitle("Odesa");
        tOdesa.setSlug("/tag/odesa");

        Tag tMykolaiv = new Tag();
        tMykolaiv.setTitle("Mykolaiv");
        tMykolaiv.setSlug("/tag/mykolaiv");

        tags.add(tOdesa);
        tags.add(tMykolaiv);

//        System.out.println(categories);
//        System.out.println(tags);

        Post p1 = new Post();
        p1.setTitle("Пост про новость в Одессе");
        p1.setCategory(cNews);
        p1.getCategory().getPosts().add(p1);
        p1.getTags().add(tOdesa);
        tOdesa.getPosts().add(p1);

        Post p2 = new Post();
        p2.setTitle("Пост про промоакцию в Николаеве");
        p2.setCategory(cPromo);
        cPromo.getPosts().add(p2);
        p2.getTags().add(tMykolaiv);
        tMykolaiv.getPosts().add(p2);

        posts.addFirst(p1);
        posts.addFirst(p2);

        // Получить все посты
        // !!! Сначала новые

        System.out.println("All Posts: " );

        System.out.println(posts);

        // Получить посты по категориям

        System.out.println("Category: " + cNews.getTitle());
        for (Post p:cNews.getPosts()) {
            System.out.println(p);
        }

        System.out.println("Category: " + cPromo.getTitle());
        for (Post p:cPromo.getPosts()) {
            System.out.println(p);
        }

        // Получить посты по тегам
        System.out.println("Tag: " + tOdesa.getTitle());
        for (Post p:tOdesa.getPosts()) {
            System.out.println(p);
        }

        System.out.println("Tag: " + tMykolaiv.getTitle());
        for (Post p:tMykolaiv.getPosts()) {
            System.out.println(p);
        }

        System.out.println("\n\n-------------------------------\n Удалим один из постов");
        // мало того, что мне нужно удалить пост из коллекции постов
        posts.remove(p1);
        // его еще нужно удалить и из коллекции постов в его категории
        p1.getCategory().getPosts().remove(p1);
        // и из коллекций во всех метках, где этот пост зафиксирован
        for (Tag t: p1.getTags()) {
            t.getPosts().remove(p1);
        }


        System.out.println("All Posts: " );

        System.out.println(posts);

        // Получить посты по категориям

        System.out.println("Category: " + cNews.getTitle());
        for (Post p:cNews.getPosts()) {
            System.out.println(p);
        }

        System.out.println("Category: " + cPromo.getTitle());
        for (Post p:cPromo.getPosts()) {
            System.out.println(p);
        }

        // Получить посты по тегам
        System.out.println("Tag: " + tOdesa.getTitle());
        for (Post p:tOdesa.getPosts()) {
            System.out.println(p);
        }

        System.out.println("Tag: " + tMykolaiv.getTitle());
        for (Post p:tMykolaiv.getPosts()) {
            System.out.println(p);
        }
    }
}
