package frontend;

import entity.Department;
import entity.Group;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Group g1 = new Group();
//        g1.setId(4);
//        g1.setName("group 4");
//
//        System.out.println(g1.getId());
//        System.out.println(g1.getName());

        Group g2  = new Group(1, "Group 2");
        System.out.println(g2.getId());
        System.out.println(g2.getName());



    }
}