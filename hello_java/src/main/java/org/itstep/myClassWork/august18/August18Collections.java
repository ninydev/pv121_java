package org.itstep.myClassWork.august18;

import org.itstep.entities.academy.Group;
import org.itstep.entities.academy.Student;
import org.itstep.exceptions.validations.NameLengthValidationMax;
import org.itstep.exceptions.validations.NameLengthValidationMin;

import java.util.*;

public class August18Collections implements Runnable
{
    @Override
    public void run() {
        System.out.println("Работа с коллекциями");

        try {
            workCollection();
        } catch (NameLengthValidationMin | NameLengthValidationMax e) {
            System.out.println(e.getMessage());
        }


    }

    public void workCollection() throws NameLengthValidationMin, NameLengthValidationMax {
        Group g = new Group("pv121");
        Student julia = new Student("Julia", g);
        Student marina = new Student("Marina", g);

        ArrayList<Student> arrayList = new ArrayList<>(10);
        arrayList.add(julia);
        arrayList.add(marina);


        List<Student> lstStudent = new ArrayList<>();
        lstStudent.add(julia);
        lstStudent.add(marina);

        arrayList.ensureCapacity(100);
        System.out.println(arrayList.size());

        Queue<Student> qStudent = new ArrayDeque<>();
        qStudent.add(julia);
        qStudent.add(marina);




        /*
        List<Student> lstStudent = new List<Student>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Student> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Student student) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Student> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Student> c) {
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

            @Override
            public Student get(int index) {
                return null;
            }

            @Override
            public Student set(int index, Student element) {
                return null;
            }

            @Override
            public void add(int index, Student element) {

            }

            @Override
            public Student remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Student> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Student> listIterator(int index) {
                return null;
            }

            @Override
            public List<Student> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        */

    }

    private void collectionFromInterface() {
        Collection<Student> col = new Collection<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Student> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Student student) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Student> c) {
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

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }
        };

    }
}
