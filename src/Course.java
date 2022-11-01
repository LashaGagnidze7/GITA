import java.util.List;
import java.util.ArrayList;

public class Course {
    private static final byte maxNumberOfStudents = 30;

    private final String name;
    private final List<Student> Students;
    private Lector assistance;
    private Lector lector;

    public Course(String name) {
        this.name = name;
        this.Students = new ArrayList<>();
    }

    public Course(String name, Lector assistance, Lector lector) {
        this(name);
        this.assistance = assistance;
        this.lector = lector;
    }

    public boolean add(Student student) throws Exception {
        if(Students.size() == maxNumberOfStudents) {
            throw new Exception("The course " + this.name + " have already " + maxNumberOfStudents + " students!");
        }
        if(Students.contains(student)) {
            throw new Exception("Student with id " + student.getId() + " is already enrolled in the course " + this.name + '!');
        }
        
        return Students.add(student);
    }

    public boolean delete(Student delete) {
        return Students.remove(delete);
    }

    public String getName() {
        return this.name;
    }

    public Lector getAssistance() {
        return this.assistance;
    }

    public void setAssistance(Lector assistance) {
        this.assistance = assistance;
    }

    public Lector getLector() {
        return this.lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }
}
