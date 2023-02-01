package controller;

import ds.graph.Graph;
import ds.graph.Vertex;
import ds.list.EduList;
import ds.list.EduNode;
import models.Grade;
import models.Student;

public class RankingHandler {
    private final Graph students;
    private final EduList studentList;
    private enum Rank {FIRST, SECOND, NON}

    public RankingHandler(EduList studentList) {
        this.students = new Graph(studentList != null ? studentList.getSize() : 0);
        this.studentList = studentList;
        createGraph();
    }

    private void createGraph() {
        EduNode student = studentList.getFirst();
        for (int i = 0; i < studentList.getSize(); i++) {
            Vertex v = new Vertex(((Student) student.getElement()).getStudentCode(), student);
            this.students.addVertex(v);
        }
        for (int i = 0; i < students.getSize(); i++) {
            Vertex v = students.getVertices()[i];
            for (int j = i + 1; j < students.getSize(); j++) {
                Vertex u = students.getVertices()[j];
                Rank result = getDirectlyBetterRank(v.getData(), u.getData());
                if (result == Rank.FIRST)
                    v.addAdjacent(u);
                else if (result == Rank.SECOND)
                    u.addAdjacent(v);
            }
        }
    }

    private Rank getDirectlyBetterRank(EduNode student1, EduNode student2) {
        int commonLesson = 0;
        int betterMarks = 0;
        EduNode grade = student1.getGrade();
        for (int i = 0; i < ((Student) student1.getElement()).getCourseNum(); i++) {
            double secondStudentMark = getMark(((Grade) grade.getElement()).getCourseCode(), student2);
            if (secondStudentMark != -1) {
                commonLesson++;
                if (((Grade) grade.getElement()).getGrade() > secondStudentMark)
                    betterMarks++;
            }
        }
        if (betterMarks > commonLesson / 2.0)
            return Rank.FIRST;
        else if (betterMarks < commonLesson / 2.0)
            return Rank.SECOND;
        else return Rank.NON;
    }

    private double getMark(int courseCode, EduNode student) {
        EduNode grade = student.getGrade();
        for (int i = 0; i < ((Student) student.getElement()).getCourseNum(); i++) {
            if (((Grade) grade.getElement()).getCourseCode() == courseCode)
                return ((Grade) grade.getElement()).getGrade();
            grade = grade.getLeftNext();
        }
        return -1;
    }

    public String compare(int student1, int student2) {
        Vertex v = this.students.findVertex(student1);
        boolean A_hasPath_B = students.hasPath(v, student2);
        Vertex u = this.students.findVertex(student2);
        boolean B_hasPath_A = students.hasPath(u, student1);
        if (A_hasPath_B && !B_hasPath_A) return ">";
        if (B_hasPath_A && !A_hasPath_B) return "<";
        else return "?";
    }
}
