public class Position {
    // gồm 2 thuộc tính là id và name(enum(DEV, TEST, PM, SCRUM_MASTER))
    int id;
    PositionName name;

    // 1 phương thức in thông tin của position đó
    public void in() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }
}
