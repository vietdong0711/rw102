//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        double diem = 4;
//        // nếu điểm >= 8 thì in ra giỏi
//        // nếu >= 6 và <8 thì in ra khá
//        // nếu >=5 và < 6 thì in ra trung bình
//        // dưới 5 thì yếu
//        if (diem >= 8) {
//            System.out.println("giỏi");
//        } else if (diem >= 6) {// sql: AND   Java : &&
//            System.out.println("khá");
//        } else if (diem >= 5) {
//            System.out.println("trung bình");
//        } else {
//            System.out.println("yếu");
//        }


        // nếu điểm >= 5 thì in ra 'qua môn'
        // nếu < 5 thì 'tạch'
//        if (diem >= 5) {  // nếu điểm >= 5 thì
//            System.out.println("qua môn"); // in ra qua môn
//        } else {  // ngược lại
//            System.out.println("tạch");// in ra tạch
//        }

//        if (dkien) {
//            thực thi nếu dkien đúng
//        } else {
//            thực thi nếu dkien sai
//        }
        // =  phép gán giá trị
        // == so sánh

        // nếu number = 0 thì in ra ra "không"
        // nếu number = 1 thì in ra ra "một"
        // nếu number = 2 thì in ra ra "hai"
        // nếu number = 3 thì in ra ra "ba"
        // các TH còn lại thì in ra "không xác định"



        //     === bài này dùng dc if-else và switch-case

        // nếu number < 0 thì in ra ra "đây là số âm"
        // nếu number < 10 thì in ra ra "đây là một số dương nhỏ hơn 10"
        // nếu number >10 thì in ra ra "đây là một số lớn hơn 10"
//        if (number < 0) {
//            System.out.println("đây là số âm");
//        } else if (number < 10) {
//            System.out.println("đây là một số dương nhỏ hơn 10");
//        } else {
//            System.out.println("đây là một số lớn hơn 10");
//        }

        // switch case, so sánh gtri ở switch và ở case là so sánh ==  , ko so sánh dc > <    >=  <=


        // Java 17
        // 1 bài toán dùng dc if-else thì CHƯA CHẮC dùng dc switch-case       A

        // 1 bài toán dùng dc switch-case thì dùng dc if-else       B

        // cùng 1 bài toán dùng dc if-else, dùng dc switch-case thì dùng cách nào? tại sao
        // X. if
        // y. switch

        // tùy từng TH : nếu có hơn 1 gtri so sánh thì dùng switch
        // nếu chỉ có 1 tri so sánh thì dùng cái nào cx dc(nên dùng if-else)
        int number = 0;
//        if (number == 0) {
//            System.out.println("không");
//        } else {
//            System.out.println("không xác định");
//        }

        // toán tử tenary(JS toán tử 3 ngôi)  - cách viêt khác của if else
        System.out.println(number == 0 ? "không" : "không xác định");
                        // dkien      ?   TH đúng   :    TH sai
//
//        switch (number) {// so sánh 1 lần
//            case 0:
//                System.out.println("không");
//                break;
//            default:
//                System.out.println("không xác định");
//        }


    }
}