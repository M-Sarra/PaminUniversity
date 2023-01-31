package models;

public class Course extends EduObject{
    private int courseCode;
    private int studentNum;

    public Course(int courseCode, String name) {
        super(name);
        this.courseCode = courseCode;
        this.studentNum = 0;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void increaseStudentNum() {
        studentNum++;
    }

    public void decreaseStudentNum() {
        studentNum--;
    }
}
