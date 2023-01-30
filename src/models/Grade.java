package models;

public class Grade {
    private int studentCode;
    private int courseCode;
    private int semester;
    private double grade;

    public Grade(int studentCode, int courseCode, int semester, double grade) {
        this.studentCode = studentCode;
        this.courseCode = courseCode;
        this.semester = semester;
        this.grade = grade;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
