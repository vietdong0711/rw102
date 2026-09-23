import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Group Java";
        group1.createDate = LocalDate.of(2020, 1, 1);

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "Group2";
        group2.createDate = LocalDate.of(2020, 1, 1);

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Java 1";
        group3.createDate = LocalDate.of(2020, 1, 1);
        Group[] groups = new Group[]{group1, group2, group3};
//        Exercise4.question6();
//        Exercise4.question8(groups);
//        Exercise4.question16();

        String[] names = new String[]{"Marketing", "Boss of director", "Accounting", "Waiting room", "Sale"};
        Exercise5.question7(names);
//        System.out.println("Marketing".compareToIgnoreCase("director"));
    }
}