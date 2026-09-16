import java.time.LocalDate;

public class Group {
    int id;
    String name;
    Account creator;
    LocalDate createDate;

    Account[] accounts;// ds các acc trong group
}