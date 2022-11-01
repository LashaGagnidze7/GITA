import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String CREATE_COURSE = "createCourse";
    public static final String DELETE_COURSE = "deleteCourse";
    public static final String CREATE_STUDENT = "createStudent";
    public static final String DELETE_STUDENT = "deleteStudent";
    public static final String CREATE_ASSISTANCE = "createAssistance";
    public static final String CREATE_PROFESSOR = "createProfessor";
    public static final String DELETE_ASSISTANCE = "deleteAssistance";
    public static final String ASSIGN_ASSISTANCE_TO_COURSE = "assignAssistanceToCourse";
    public static final String ASSIGN_PROFESSOR_TO_COURSE = "assignProfessorToCourse";
    public static final String ADD_STUDENT_TO_COURSE = "addStudentToCourse";
    public static final String ADD_STUDENTS_TO_COURSE = "addStudentsToCourse";
    public static final String REMOVE_STUDENT_FROM_COURSE = "removeStudentFromCourse";
    public static void main(String[] args) {
        UniManagementImpl TSU = new UniManagementImpl();

        try {
            System.out.println("Starting the university!");
            String[] arguments = scanner.nextLine().split(" ");
            String command = arguments[0];
            while (!command.equals("Exit")) {
                switch (command) {
                    case CREATE_STUDENT -> TSU.createStudent(Integer.parseInt(arguments[1]), arguments[2], arguments[3], arguments[4]);
                    case DELETE_STUDENT -> TSU.deleteStudent(Integer.parseInt(arguments[1]));
                    case CREATE_COURSE -> TSU.createCourse(arguments[1]);
                    case DELETE_COURSE -> TSU.deleteCourse(arguments[1]);
                    case CREATE_ASSISTANCE -> TSU.createAssistance(Integer.parseInt(arguments[1]), arguments[2], arguments[3]);
                    case CREATE_PROFESSOR -> TSU.createAssistance(Integer.parseInt(arguments[1]), arguments[2], arguments[3], LectorType.valueOf(arguments[4]));
                    case DELETE_ASSISTANCE -> TSU.deleteAssistance(Integer.parseInt(arguments[1]));
                    case ASSIGN_ASSISTANCE_TO_COURSE -> TSU.assignAssistanceToCourse(new Lector(Integer.parseInt(arguments[1])), new Course(arguments[2]));
                    case ASSIGN_PROFESSOR_TO_COURSE -> TSU.assignProfessorToCourse(new Lector(Integer.parseInt(arguments[1])), new Course(arguments[2]));
                    case ADD_STUDENT_TO_COURSE -> TSU.addStudentToCourse(new Student(Integer.parseInt(arguments[1])), new Course(arguments[2]));
                    case ADD_STUDENTS_TO_COURSE -> {
                        Student[] students = new Student[arguments.length-1];
                        for (int i = 1; i < arguments.length-1; i++) {
                            students[i] = new Student(Integer.parseInt(arguments[i]));
                        }
                        TSU.addStudentsToCourse(students, new Course(arguments[arguments.length-1]));
                    }
                    case REMOVE_STUDENT_FROM_COURSE -> TSU.removeStudentFromCourse(new Student(Integer.parseInt(arguments[1])), new Course(arguments[2]));
                    default -> System.out.println("Invalid Instruction!");
                }

                System.out.println(TSU.courses);
                System.out.println(TSU.students);
                System.out.println(TSU.assistance);
                System.out.println(TSU.docentsAndProfessors);
                arguments = scanner.nextLine().split(" ");
                command = arguments[0];
                System.out.println(Arrays.toString(arguments));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Goodbye!");
        }
    }
}
