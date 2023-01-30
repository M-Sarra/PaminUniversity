package models;

public class Student extends EduObject{
    private int studentCode;
    private int courseNum;

    public Student(int studentCode, String name) {
        super(name);
        this.studentCode = studentCode;
        this.courseNum = 0;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void increaseCourseNum() {
        courseNum++;
    }

    public void decreaseCourseNum() {
        courseNum--;
    }
}
