package controller;

import ds.graph.Graph;
import ds.graph.GraphList;
import ds.graph.Vertex;
import ds.list.EduList;
import ds.list.EduNode;
import models.Course;
import models.Grade;

public class RelationHandler {
    private final Graph courses;
    private final EduList coursesList;
    private boolean isCreated;

    public RelationHandler(EduList courses) {
        this.courses = new Graph(courses != null ? courses.getSize() : 0);
        this.coursesList = courses;
        this.isCreated = false;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public void createGraph() {
        EduNode course = coursesList.getFirst();
        for (int i = 0; i < coursesList.getSize(); i++) {
            Vertex  v = new Vertex(((Course) course.getElement()).getCourseCode(), course);
            this.courses.addVertex(v);
        }
        for (int i = 0; i < courses.getSize(); i++) {
            Vertex v = courses.getVertices()[i];
            for (int j = 0; j < courses.getSize(); j++) {
                Vertex u = courses.getVertices()[j];
                if (!v.equals(u)) {
                    if (isDirectlyRelative(v.getData(), u.getData())) {
                        v.addAdjacent(u);
                        u.addAdjacent(v);
                    }
                }
            }
        }
    }

    public boolean isDirectlyRelative(EduNode course1, EduNode course2) {
        int count = 0;
        EduNode grade = course1.getGrade();
        for (int i = 0; i < ((Course) course1.getElement()).getStudentNum(); i++) {
            if (contains(((Grade) grade.getElement()).getStudentCode(), course2))
                count++;
            grade = grade.getRightNext();
        }
        int size1 = ((Course) course1.getElement()).getStudentNum();
        int size2 = ((Course) course2.getElement()).getStudentNum();
        return count >= size1/2.0 && count >= size2/2.0;
    }

    private boolean contains(int studentCode, EduNode course2) {
        EduNode grade = course2.getGrade();
        for (int i = 0; i < ((Course) course2.getElement()).getStudentNum(); i++) {
            if (((Grade) grade.getElement()).getStudentCode() == studentCode)
                return true;
            grade = grade.getRightNext();
        }
        return false;
    }

    public String isRelative(int course1, int course2) {
        Vertex v = this.courses.findVertex(course1);
        boolean hasPath = courses.hasPath(v, course2);
        return hasPath ? "yes" : "no";
    }

    public String getAllRelative(int course) {
        GraphList relativeList = this.courses.getComponent(course);
        Vertex v = sort(relativeList.getFirst());
        StringBuilder relatives = new StringBuilder();
        while (v != null) {
            relatives.append(" ").append(v.getCode());
            v = v.getNext();
        }
        return relatives.toString();
    }

    private Vertex sort(Vertex v) {
        if (v.getNext() != null) {
            Vertex[] vertices = split(v);
            Vertex v1 = vertices[0];
            Vertex v2 = vertices[1];
            sort(v1);
            sort(v2);
            v = merge(v1, v2);
        }
        return v;
    }

    private Vertex merge(Vertex v1, Vertex v2) {
        if (v1 == null)
            return v2;
        if (v2 == null)
            return v1;
        if (v1.getCode() <= v2.getCode()) {
            v1.setNext(merge(v1.getNext(), v2));
            return v1;
        }
        else {
            v2.setNext(merge(v1, v2.getNext()));
            return v2;
        }
    }

    private Vertex[] split(Vertex v) {
        if (v == null)
            return new Vertex[]{null, null};
        if (v.getNext() == null)
            return new Vertex[]{null, v};
        Vertex v1 = v;
        Vertex v2 = v.getNext();
        Vertex[] vertices = split(v2.getNext());
        v1.setNext(vertices[0]);
        v2.setNext(vertices[1]);
        return new Vertex[]{v1, v2};
    }
}
