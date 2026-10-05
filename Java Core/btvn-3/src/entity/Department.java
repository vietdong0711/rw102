package entity;

public class Department {
    int id;
    public String name;

    public Department() {
    }

    public Department(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}