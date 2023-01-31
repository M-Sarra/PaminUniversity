package controller;

import ds.hashTable.DynamicTable;
import ds.list.EduNode;
import models.Course;
import models.Grade;
import models.Student;

public class TableHandler {
    private final DynamicTable students;
    private final DynamicTable courses;

    public TableHandler(int a, int b, int p) {
        this.students = new DynamicTable(a, b, p);
        this.courses = new DynamicTable(a, b, p);
    }

    public void insertStudent(EduNode student, int code) {
        students.insert(student, code);
    }

    public void insertCourse(EduNode course, int code) {
        courses.insert(course, code);
    }

    public void deleteStudent(int code) {
        students.delete(code);
    }

    public void deleteCourse(int code) {
        courses.delete(code);
    }

    public String findStudentData(int code) {
        int hash = students.hash(code, students.getSize());
        EduNode node = students.search(code);
        if (node == null) return null;
        Student student = (Student) node.getElement();
        String studentData = "[" + student.getStudentCode() + "] [" +
                student.getName() + "] [" + student.getCourseNum() + "]";
        String coursesData = "";
        EduNode gradeNode = node.getGrade();
        for (int i = 0; i < student.getCourseNum(); i++) {
            Grade grade = (Grade) gradeNode.getElement();
            coursesData = "[" + grade.getCourseCode() + "] [" +
                    grade.getSemester() + "] [" + grade.getGrade() + "]" +
                    "\n" + coursesData;
            gradeNode = gradeNode.getLeftNext();
        }
        return hash + "\n" + studentData + "\n" + coursesData;
    }

    public String findCourseData(int code) {
        int hash = courses.hash(code, courses.getSize());
        EduNode node = courses.search(code);
        if (node == null) return null;
        Course course = (Course) node.getElement();
        String courseData = "[" + course.getCourseCode() + "] [" +
                course.getName() + "] [" + course.getStudentNum() + "]";
        String studentsData = "";
        EduNode gradeNode = node.getGrade();
        for (int i = 0; i < course.getStudentNum(); i++) {
            Grade grade = (Grade) gradeNode.getElement();
            studentsData = "[" + grade.getStudentCode() + "] [" +
                    grade.getSemester() + "] [" + grade.getGrade() + "]" +
                    "\n" + studentsData;
            gradeNode = gradeNode.getRightNext();
        }
        return "[" + hash + "]" + "\n" + courseData + "\n" + studentsData;
    }

}
