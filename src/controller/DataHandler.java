package controller;

import ds.list.EduList;
import ds.list.EduNode;
import ds.list.GradeList;
import models.Course;
import models.Grade;
import models.Student;

public class DataHandler {
    private final EduList students;
    private final EduList courses;
    private final GradeList grades;

    public DataHandler() {
        students = new EduList();
        courses = new EduList();
        grades = new GradeList();
    }

    public void addStudent(int studentCode, String studentName) {
        Student student = new Student(studentCode, studentName);
        this.students.insert(student);
    }

    public void addCourse(int courseCode, String courseName) {
        Course course = new Course(courseCode, courseName);
        this.courses.insert(course);
    }

    public void addGrade(int studentCode, int courseCode,
                         int semesterCode, double grade) {
        Grade newGrade = new Grade(studentCode, courseCode,
                semesterCode, grade);
        EduNode student = findStudent(studentCode);
        EduNode course = findCourse(courseCode);
        EduNode n = this.grades.insert(newGrade, student, student != null ? student.getGrade() : null,
                course, course != null ? course.getGrade() : null);
        if (student != null) {
            student.setGrade(n);
            ((Student) student.getElement()).increaseCourseNum();
        }
        if (course != null) {
            course.setGrade(n);
            ((Course) course.getElement()).increaseStudentNum();
        }
    }

    private EduNode findStudent(int studentCode) {
        EduNode n = students.getFirst();
        for (int i = 0; i < students.getSize(); i++) {
            if (((Student) n.getElement()).getStudentCode() == studentCode)
                return n;
            else n = n.getNext();
        }
        return null;
    }

    private EduNode findCourse(int courseCode) {
        EduNode n = courses.getFirst();
        for (int i = 0; i < courses.getSize(); i++) {
            if (((Course) n.getElement()).getCourseCode() == courseCode)
                return n;
            else n = n.getNext();
        }
        return null;
    }

    public void editStudent(int studentCode, String studentName) {
        EduNode n = students.getFirst();
        for (int i = 0; i < students.getSize(); i++) {
            if (((Student) n.getElement()).getStudentCode() == studentCode) {
                ((Student) n.getElement()).setName(studentName);
            }
            else n = n.getNext();
        }
    }

    public void editCourse(int courseCode, String courseName) {
        EduNode n = courses.getFirst();
        for (int i = 0; i < courses.getSize(); i++) {
            if (((Course) n.getElement()).getCourseCode() == courseCode) {
                ((Course) n.getElement()).setName(courseName);
            }
            else n = n.getNext();
        }
    }

    public void editGrade(int studentCode, int courseCode, double newGrade) {
        EduNode n = students.getFirst();
        for (int i = 0; i < students.getSize(); i++) {
            if (((Student) n.getElement()).getStudentCode() == studentCode) {
                EduNode grade = n.getGrade();
                for (int j = 0; j < ((Student) n.getElement()).getCourseNum(); j++) {
                    if (((Grade) grade.getElement()).getCourseCode() == courseCode) {
                        ((Grade) grade.getElement()).setGrade(newGrade);
                    }
                    else grade = grade.getLeftNext();
                }
            }
            else n = n.getNext();
        }
    }

    public void deleteStudent(int studentCode) {
        EduNode student = findStudent(studentCode);
        students.delete(student);
        if (student != null) {
            EduNode grade = student.getGrade();
            for (int i = 0; i < ((Student) student.getElement()).getCourseNum(); i++) {
                grades.delete(grade);
                grade = grade.getLeftNext();
            }
        }
    }

    public void deleteCourse(int courseCode) {
        EduNode course = findCourse(courseCode);
        courses.delete(course);
        if (course != null) {
            EduNode grade = course.getGrade();
            for (int i = 0; i < ((Course) grade.getElement()).getStudentNum(); i++) {
                grades.delete(grade);
                grade = grade.getRightNext();
            }
        }
    }

    public void deleteGrade(int studentCode, int courseCode) {
        EduNode student = findStudent(studentCode);
        EduNode course = findCourse(courseCode);
        EduNode grade;
        if (student != null) {
            grade = student.getGrade();
            for (int j = 0; j < ((Student) student.getElement()).getCourseNum(); j++) {
                if (((Grade) grade.getElement()).getCourseCode() == courseCode) {
                    grades.delete(grade);
                }
                else grade = grade.getLeftNext();
            }
            if (student.getGrade().equals(grade)) {
                student.setGrade(grade.getLeftNext());
            }
            if (course != null) {
                if (course.getGrade().equals(grade))
                    course.setGrade(grade.getRightNext());
                ((Course) course.getElement()).decreaseStudentNum();
            }
            ((Student) student.getElement()).decreaseCourseNum();
        }
    }

    public int getNumberC(int studentCode) {
        EduNode student = findStudent(studentCode);
        if (student != null) {
            return ((Student) student.getElement()).getCourseNum();
        }
        return 0;
    }

    public int getNumberS(int courseCode) {
        EduNode course = findCourse(courseCode);
        if (course != null) {
            return ((Course) course.getElement()).getStudentNum();
        }
        return 0;
    }
}
