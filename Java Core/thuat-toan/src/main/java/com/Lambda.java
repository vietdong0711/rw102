package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lambda {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("An", "Nga", "Nam", "Anh", "Thanh", "Minh", "Minh");

        // lấy ra ds các tên có chứa chữ n trong tên
//        List<String> rs = new ArrayList();
//        for(String s : strings){
//            if (s.contains("n")) {   // lọc
//                rs.add(s);
//            }
//        }
        //laasay ra ptu dau tien có chữ n trong tên
//        String rs = strings.stream()
//                .filter(s ->s.contains("n"))
//                .findFirst().orElse(null);

        // đém so ptu trong tên có chữ n
//        int count = 0;
//        for(String s : strings){
//            if (s.contains("n")) {   // lọc
//                count++;
//            }
//        }
//        long count = strings.stream()
//                .filter(s -> s.contains("n"))
//                        .count();

        List<String> rs = strings.stream().filter(s ->s.contains("n"))
                .limit(3).collect(Collectors.toList());

//      .stream(): thay for
//      .filter(:  lọc  ~   if()
//       .collect(   gom lại
//        .findFirst() lấy ra ptu đầu tiên
//        .findFirst().orElse("");  nếu tu dau tiên null thì  trả ra ""
//        .count();   đếm
//        .limit()    giống limit trong sql

        // input: list   output: list,   ptu, số lượng

        // list  -> map





//        System.out.println(String.join(" ", rs));

        // tìm ra vị trí của bạn tên là Anh
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).equals("Anh")) {
                System.out.println("Vị trí của Anh là: " + i);
                break;
            }
        }


    }

}
