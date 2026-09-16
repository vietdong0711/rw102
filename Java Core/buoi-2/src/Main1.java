public class Main1 {
    public static void main(String[] args) {

        String[] hocSinhs = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
                                        //0     1   2    3    4     5   6    7      8
        // in ra tất cả các học sinh trong ds trên
        // vòng lặp:   xử lý 1 chuỗi hành động được lặp đi lặp lại
//        System.out.println("For j");
//        // index: xử lý theo ví trị của ptu trong ds
//        for (int j = 0; j < hocSinhs.length; j++) {
//            System.out.println(hocSinhs[j]);
//        }
//
//        System.out.println("For each");// in theo object
//        // gán lần lượt các ptu trong mảng với 1 object, sau khi khi xử lý xong thì với ptu tiếp theo
//        // gán cho đến khi hết ds thì dừng vòng for
//        for (String hs : hocSinhs) {
//            System.out.println(hs);
//        }

        //??? khi nào dùng fori,, khi nào dùng foreach
        // khi nào cần sử dụng đến vị trí thfi dùng fori
        // ko cần dùng vtri thì dùng foreach

        // tìm ra vị trí của hs có tên là 'G' trong ds
//        for (int j = 0; j < hocSinhs.length; j++) {
//            if (hocSinhs[j] == "G") {
//                System.out.println("Học sinh cần tìm ở vị trí số " + (j+1) + " trong danh sách!");
//            }
//        }

        // kiểm tra xem có học sinh nào có tên là 'G' ko?
//        boolean check = false;
//        for (int j = 0; j <= hocSinhs.length; j++) {
//            if (hocSinhs[j] == "G") {
//                check =  true;
//            }
//        }
//        for (String hs: hocSinhs) {
//            if (hs == "G") {
//                check = true;
//            }
//        }
//        if (check == true) {
//            System.out.println("có học sinh tên G");
//        } else {
//            System.out.println("Ko có học sinh tên G");
//        }

        //1,  j: vị tri các ptu trong mảng;  j bắt đầu từ 0
        //2,  j < hocSinhs.length;   nếu j >= độ dài của array thì dừng vòng lặp - dkien dừng vòng lặp // 8<8  - ko thõa mãn  -> dừng vòng lặp
        //3,  j++    <=>   j = j + 1 (tăng j lên 1 đơn vị sau mỗi lần lặp)

        // for j: index   -- lặp theo vị trí
//
//        for (int j = hocSinhs.length - 1;  j >= 0; j--) {
//            System.out.println(hocSinhs[j]);
//        }
        //in từ 10 - 1
//        for (int j = 10; j  >= 1; j--) {
//            System.out.println(j);
//        }

        // in ra từ 1- 10
//        System.out.println("FOR");// với lần lặp có thể biết trước
//        for (int j = 1; j <= 10; j++) {// lặp 10 lần
//            System.out.println(j);
//        }.




//
        // vòng lặp, for làm dc thì while cx làm dc
        System.out.println("WHILE");
        int i = 10;
        while (i <= 5) {// check dkien nếu thõa mãn thì mới thực thi
            System.out.println(i);
            i++;
        }
        System.out.println("DO-WHILE");// lặp với số lần chưa biest trước
        int j = 10;
        do {// thực thi 1 lần trước rồi mới check dkieeuf kiện
            System.out.println(j);
            j++;
        } while (j <= 5);

    }
}

