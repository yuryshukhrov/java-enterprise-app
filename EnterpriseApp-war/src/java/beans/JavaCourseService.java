package beans;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaCourseService {

    private static final ArrayList<JavaCourse> javaCourseList =
            new ArrayList<JavaCourse>(Arrays.asList(
            new JavaCourse("Java Programming"),
            new JavaCourse("Applied Java Programming"),
            new JavaCourse("Java Web Technology"),
            new JavaCourse("Java Enterprise Technology")));

    public JavaCourseService() {
    }

    public ArrayList<JavaCourse> getJavaCourses() {
        return javaCourseList;
    }
}
