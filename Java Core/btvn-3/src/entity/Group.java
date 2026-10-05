package entity;

import java.time.LocalDate;

public class Group {
    private int id;
    private String name;
    private LocalDate createDate;

    public Group() {

    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(LocalDate createDate, int id, String name) {
        this.createDate = createDate;
        this.id = id;
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "entity.Group{" +
                "createDate=" + createDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}