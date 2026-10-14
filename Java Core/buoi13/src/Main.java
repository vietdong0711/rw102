import java.lang.reflect.Array;
import java.util.*;
import java.util.zip.ZipError;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        List<String> arrs = new ArrayList<>();

        arrs.add("A");
        arrs.add("B");
        arrs.add("A");
        String x = new String("X");
        arrs.add(x);

//        System.out.println(arrs.get(1));//   arr[1]
//        System.out.println(arrs.size());
//        arrs.remove(0);
//        for (String s : arrs) {
//            System.out.print(s + ", ");
//        }
//        System.out.println();
//
//        arrs.remove(x);
//        for (String s : arrs) {
//            System.out.print(s + ", ");
//        }
        //  key     value      : key(unique)
        //  nhóm theo studentID
//        Map<Integer, Student> mapStudentById = new Hashtable<>();
//        mapStudentById.put(1, new Student(1, "A1"));
//        mapStudentById.put(2, new Student(2, "A2"));
//        mapStudentById.put(3, new Student(3, "A3"));
//        mapStudentById.put(4, new Student(4, "A4"));
//        mapStudentById.put(5, new Student(4, "A4"));
//        System.out.println("");
//
//        System.out.println("a".equals("b"));
//        System.out.println("a".hashCode());
//        System.out.println("b".hashCode());

        Student s1 = new Student(1, "A", 10.0);
        Student s2 = new Student(2, "B", 6.1);
        Student s3 = new Student(4, "C", 8.1);
        Student s4 = new Student(1, "D", 3.1);
        Student s5 = new Student(3, "E", 9.1);

        Student[] students = new Student[]{s1, s2, s3, s4, s5};
        System.out.println("Truoc khi sap xep");
        for (Student s: students) {
            System.out.println(s);
        }
        // sủi bọt
//        for (int i= 0; i< students.length -1; i++) {
//            for (int j = i+1; j < students.length; j++) {
//                if (students[i].getGpa() > students[j].getGpa()) {
//                    // đổi chổ: swap
//                    Student temp = students[i];
//                    students[i] = students[j];
//                    students[j] = temp;
//                }
//            }
//        }
        Arrays.sort(students);


        System.out.println("Truoc khi sap xep");
        for (Student s: students) {
            System.out.println(s);
        }




    }
}