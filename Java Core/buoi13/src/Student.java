public final class Student implements Comparable<Student> {

    private int id;
    private String name;
    private double gpa;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "gpa=" + gpa +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public final void in() {
        System.out.println("Đây là student");
    }

    // định nghĩa lại cách mà 2 object so sánh với nhau
    @Override
    public int compareTo(Student o) {
        if (this.id > o.getId()) {
            return 1;
        } else if (this.id < o.getId()) {
            return -1;
        } else if (this.id == o.getId()) {
            if (this.getGpa() > o.getGpa()) {
                return 1;
            } else if (this.getGpa() < o.getGpa()){
                return -1;
            }
        }
        return 0;
    }
}
