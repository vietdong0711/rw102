package com;

import java.util.List;

public class Lambda2 {

    public static void main(String[] args) {
        Student s1 = new Student(1, "An", "1A");
        Student s2 = new Student(2, "Nga", "1A");
        Student s3 = new Student(3, "Nam", "1A");
        Student s4 = new Student(4, "Anh", "1B");
        Student s5 = new Student(5, "Thanh", "1C");
        Student s6 = new Student(6, "Minh", "1D");
        Student s7 = new Student(7, "Lan", "1B");
        Student s8 = new Student(8, "Hoa", "1A");
        Student s9 = new Student(9, "Binh", "1D");
        Student s10 = new Student(10, "Vy", "1A");

        List<Student> students = List.of(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);

    }




}
