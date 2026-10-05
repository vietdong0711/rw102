package entity;

import java.time.LocalDate;

public class Account {
    // sửa tên thuộc tính   shift + F6
    private int id;
    private String email;
    private String username;
    private String fullName;
    private Department department;//phòng ban của account đó đang tham gia
    private Position position;
    private LocalDate createDate;

    public Account() {

    }

    public Account(String username, Position position, int id, String fullName, String email, Department department, LocalDate createDate) {
        this.username = username;
        this.position = position;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.createDate = createDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}