package org.itstep.myClassWork.august19.posts;

import org.itstep.myClassWork.august19.posts.models.Category;
import org.itstep.myClassWork.august19.posts.models.Post;
import org.itstep.myClassWork.august19.posts.models.Tag;

import java.util.*;

public class MyBlog implements Collection<Post>
{
    HashSet<Tag> tags = new HashSet<>();
    HashSet<Category> categories = new HashSet<>();
    ArrayDeque<Post> posts = new ArrayDeque<>();

    @Override
    public boolean add(Post post) {
        // Сохранил пост в главную коллекцию
        posts.add(post);
        // Сохраняем категорию (будет создана, если такой нет)
        categories.add(post.getCategory());
        // Устанавливает связь Категории с постом
        post.getCategory().getPosts().add(post);

        // Проходим по коллекции меток
        // Если таких меток еще небыло - создаем их
        // Для всех меток создаем в коллекции постов новый
        for (Tag t: post.getTags()) {
            tags.add(t);
            t.getPosts().add(post);
        }

        return true;
    }

    @Override
    public boolean remove(Object post) {
        if(post instanceof Post) {
            // Удаляю из главной коллекции постов
            posts.remove(post);
            // Удаляю пост из списка постов категории
            ((Post) post).getCategory().getPosts().remove(post);
            // Удаляю пост из спикосв постов для каждой метки
            for (Tag t: ((Post) post).getTags()) {
                tags.add(t);
                t.getPosts().remove(post);
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return posts.size();
    }

    @Override
    public boolean isEmpty() {
        return posts.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Post> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return posts.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Post> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
