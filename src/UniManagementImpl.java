import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class UniManagementImpl implements UniManagement{
    private static final short maxNumberOfStudentsInUniversity = 1000;
    private static final byte maxNumberOfCourses = 10;

    private int lastUsedStudentIndex = 0;
    List<Student> students = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Lector> assistance = new ArrayList<>();
    List<Lector> docentsAndProfessors = new ArrayList<>();
        
    @Override
    public Course createCourse(String courseName) throws Exception {
        Predicate<Course> exists = course -> course.getName().equals(courseName);
        if(courses.stream().noneMatch(exists)) {
            if(courses.size() == maxNumberOfCourses) {
                throw new Exception("The university have already " + maxNumberOfCourses + " courses!");
            }
            Course course = new Course(courseName);
            courses.add(course);
            return course;
        } else {
            throw new Exception("The Course named " + courseName + " already exist in the university!");
        }
    }

    @Override
    public boolean deleteCourse(String courseName) throws CourseNotFoundException {
        Predicate<Course> exists = course -> course.getName().equals(courseName);
        if(courses.stream().anyMatch(exists)){
            courses.removeIf(course -> course.getName().equals(courseName));
            return true;
        } else {
            throw new CourseNotFoundException();
        }
    }

    @Override
    public Student createStudent(int id, String firstName, String lastName, String factNumber) throws Exception {
        Predicate<Student> exists = student-> student.getId() == id;
        if(students.stream().noneMatch(exists)) {
            if(students.size() == maxNumberOfStudentsInUniversity) {
                throw new Exception("The university have already " + maxNumberOfStudentsInUniversity + " students!");
            }
            Student student = new Student(id, firstName, lastName, factNumber);
            students.add(student);
            lastUsedStudentIndex++;
            return student;
        } else {
            throw new Exception("The Student with id " + id + " is already in the university!");
        }
    }

    @Override
    public boolean deleteStudent(int id) throws StudentNotFoundException {
        Predicate<Student> exists = student-> student.getId() == id;
        if(students.stream().anyMatch(exists)){
            students.removeIf(student -> student.getId() == id);
            lastUsedStudentIndex--;
            return true;
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public Lector createAssistance(int id, String firstName, String lastName) {
        Predicate<Lector> exists = assistance-> assistance.getId() == id;
        if(assistance.stream().noneMatch(exists)) {
            Lector assistance = new Lector(id, firstName, lastName);
            this.assistance.add(assistance);
            return assistance;
        }
        return null;
    }

    public Lector createAssistance(int id, String firstName, String lastName, LectorType lectorType) {
        Predicate<Lector> exists = assistance-> assistance.getId() == id;
        if(assistance.stream().noneMatch(exists)) {
            Lector assistance = new Lector(id, firstName, lastName, lectorType);
            this.assistance.add(assistance);
            return assistance;
        }
        return null;
    }

    @Override
    public boolean deleteAssistance(int id) throws Exception {
        Predicate<Lector> exists = assistance-> assistance.getId() == id;
        if(assistance.stream().anyMatch(exists)) {
            assistance.removeIf(lector -> lector.getId() == id);
            return true;
        } else {
            throw new Exception("Assistance not found!");
        }
    }

    @Override
    public boolean assignAssistanceToCourse(Lector assistance, Course course) throws Exception {
        Predicate<Lector> exists = assistance1-> assistance1.getId() == assistance.getId();
        Predicate<Course> exists1 = course1 -> course1.getName().equals(course.getName());
        if (this.assistance.stream().anyMatch(exists) && courses.stream().anyMatch(exists1)) {
            for (Course course1 : courses) {
                if (course1.getName().equals(course.getName())) {
                    course1.setAssistance(assistance);
                    return true;
                }
            }
        } else {
            throw new Exception("Assistance or Course not found!");
        }

        return false;
    }

    @Override
    public boolean assignProfessorToCourse(Lector professor, Course course) throws Exception {
        Predicate<Lector> exists = professor1 -> professor1.getId() == professor.getId();
        Predicate<Course> exists1 = course1 -> course1.getName().equals(course.getName());
        if (docentsAndProfessors.stream().anyMatch(exists) && courses.stream().anyMatch(exists1)) {

            for (Course course2 : courses) {
                if (course2.getName().equals(course.getName())) {
                    course2.setLector(professor);
                    return true;
                }
            }
        } else {
            throw new Exception("Professor or Course not found!");
        }
        return false;
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) throws Exception {
        Predicate<Student> exists = student1 -> student1.getId() == student.getId();
        Predicate<Course> exists1 = course1 -> course1.getName().equals(course.getName());
        if(students.stream().anyMatch(exists) && courses.stream().anyMatch(exists1)) {
            for(Student student1 : students) {
                if (student1.getId() == student.getId()) {
                    student1.add(course);
                }
            }
            for(Course course1 : courses) {
                if(course1.getName().equals(course.getName())) {
                    course1.add(student);
                }
            }
            return true;
        } else {
            throw new Exception("Student or Course not found!");
        }
    }

    @Override
    public boolean addStudentsToCourse(Student[] students, Course course) {
        try {
            for (Student student : students) {
                addStudentToCourse(student, course);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course) throws Exception {
        Predicate<Student> exists = student1 -> student1.getId() == student.getId();
        Predicate<Course> exists1 = course1 -> course1.getName().equals(course.getName());
        if(students.stream().anyMatch(exists) && courses.stream().anyMatch(exists1)) {
            for (Student student1 : students) {
                if(student1.getId() == student.getId()) {
                    student1.delete(course);
                }
            }

            for (Course course1 : courses) {
                if(course1.getName().equals(course.getName())) {
                    course1.delete(student);
                }
            }
            return true;
        } else {
            throw new Exception("Student or Course not found!");
        }
    }
    
}
