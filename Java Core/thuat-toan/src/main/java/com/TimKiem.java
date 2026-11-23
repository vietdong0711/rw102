package com;

public class TimKiem {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,  12};
        // tìm kiếm tuần tự  chạy đầu -> cuối   : TH xấu nhát lặp 1000 lần

        // tìm vtri của ptu có giá trị là x
        //    lấy ptu ở giữa  so sánh vs gti cần tìm
            // nếu   mid >  x   >>>>  cần tìm nằm ở bên trái mid
            // nếu   mid <  x   >>>>  cần tìm nằm ở bên phải mid
        int x = 11;

        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end)/2;
        while (start <= end) {
            if (arr[mid] == x) {
                System.out.println("Tìm thấy tại vị trí: " + mid);
                return;
            } else if (x < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end)/2;
        }
        System.out.println("Không tìm thấy");
    }
}
