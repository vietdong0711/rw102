package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lambda1 {
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
        // lấy 1 map KEY: name,    value: Student học sinh tương ứng
//        Map<String, Student> mapByName = new HashMap<>();
//        for (Student student : students) {
//            mapByName.put(student.getName(), student);
//        }
//        Map<String, Student> mapByName = students.stream()
//                .collect(Collectors.toMap(s -> s.getName(), s -> s));
//                                                    // key                 // value

        //

        // lấy ra map key:tên lớp,   value : ds các hs trong lớp
        // nếu lớp đó chưa ồn tại ở key của map thì thêm mới 1 List vào value
        // nếu lớp đó đã ton tại ỏ key,   add student đó vào list ở value

//        Map<String, List<Student>> mapByClass = new HashMap<>();
//        for (Student s : students) {
//            String className = s.getClazz();
//            if (mapByClass.get(className) == null) {// key nào chưa ton tai ở map
//                List<Student> list = new ArrayList<>();
//                list.add(s);
//                mapByClass.put(className, list);
//            }  else {// key đã tồn tại
//                List<Student> list = mapByClass.get(className);// list theo key
//                list.add(s);
//                mapByClass.put(className, list);
//            }
//        }
//        Map<String, List<Student>> mapByClazz = students.stream()
//                        .collect(Collectors.groupingBy(s -> s.getClazz()));

        int a = 1;
        int b = 2;



        System.out.println("");


    }
}
