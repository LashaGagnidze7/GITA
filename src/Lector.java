import java.util.List;
import java.util.ArrayList;

public class Lector extends User{
    private static final byte maxNumberOfCourses = 4;

    private LectorType lectorType;
    private final List<Course> courses;

    public Lector(int id) {
        super(id, "", "");
        this.courses = new ArrayList<>();
    }
    
    public Lector(int id, String firstName, String lastName) {
        this(id, firstName, lastName, LectorType.ASSISTANCE);
    }

    public Lector(int id, String firstName, String lastName, LectorType lectorType) {
        super(id, firstName, lastName);
        this.courses = new ArrayList<>();
        this.lectorType = lectorType;
    }

    public LectorType getLectorType() {
        return this.lectorType;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public boolean add(Course course) throws Exception{
        if(courses.size() == maxNumberOfCourses) {
            throw new Exception("Professor with id " + this.getId() + " already teach " + maxNumberOfCourses + " courses!");
        }
        if(courses.contains(course)) {
            throw new Exception("Professor with id " + this.getId() + " already teach the course " + course.getName() + '!');
        }
        
        return courses.add(course);
    }
    
    public boolean delete(Course course) {
        return courses.remove(course);
    }
    
}
