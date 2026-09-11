public class Program1 {
    public static void main(String[] args) {
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";

        // đổi tên từ department1 -> dep3 và 2 row dưới sửa theo
        // bôi đen -> chuột phải -> rename
        Department dep2 = new Department();
        dep2.id = 2;
        dep2.name = "Bảo vệ";

        Department dep3 = new Department();
        dep3.id = 2;
        dep3.name = "Marketing";

        // ctrl + D : duplicate row
        System.out.println("Department ID: " + department1.id);
        System.out.println("Department Name: " + department1.name);

    }
}
