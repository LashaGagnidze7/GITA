import java.util.List;
import java.util.ArrayList;

public class Student extends User{
    private static final byte maxNumberOfCourses = 10;

    private final String facNumber;
    private final List<Course> courses;

    public Student(int id) {
        super(id, "", "");
        this.facNumber = "";
        this.courses = new ArrayList<>();
    }
    public Student(int id, String firstName, String lastName, String facNumber) {
        super(id, firstName, lastName);
        this.facNumber = facNumber;
        this.courses = new ArrayList<>();
    }

    public boolean add(Course course) throws Exception{
        if(courses.size() == maxNumberOfCourses) {
            throw new Exception("Student with id " + this.getId() + " have already " + maxNumberOfCourses + " courses!");
        }
        if(courses.contains(course)) {
            throw new Exception("Student with id " + this.getId() + " is already enrolled in the course " + course.getName() + '!');
        }
        
        return courses.add(course);
    }
    
    public boolean delete(Course course) {
        return courses.remove(course);
    }   

    public String getFacNumber() {
        return this.facNumber;
    }

    public List<Course> getCourses() {
        return this.courses;
    }
}
