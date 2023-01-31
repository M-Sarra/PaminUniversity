package controller;

import ds.list.EduNode;
import ds.tree.EduTree;
import models.Course;
import models.Grade;
import models.Student;

public class TreeHandler {
    private final EduTree students;
    private final EduTree courses;

    public TreeHandler() {
        students = new EduTree();
        courses = new EduTree();
    }

    public void insertStudent(EduNode student) {
        students.insert(student);
    }

    public void insertCourse(EduNode course) {
        courses.insert(course);
    }

    public void editStudent(EduNode student, String prevName) {
        students.RB_delete(prevName);
        students.insert(student);
    }

    public void editCourse(EduNode course, String prevName) {
        courses.RB_delete(prevName);
        courses.insert(course);
    }

    public void deleteStudent(String name) {
        students.RB_delete(name);
    }

    public void deleteCourse(String name) {
        courses.RB_delete(name);
    }

    public String findStudentData(String name) {
        EduNode node = students.RB_Search(name);
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
        return studentData + "\n" + coursesData;
    }

    public String findCourseData(String name) {
        EduNode node = courses.RB_Search(name);
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
        return courseData + "\n" + studentsData;
    }
}
