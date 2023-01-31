package request_response;

import controller.DataHandler;
import controller.TableHandler;
import controller.TreeHandler;

public class RequestHandler {
    private final DataHandler dataHandler;
    private final TreeHandler treeHandler;
    private final TableHandler tableHandler;

    public RequestHandler(int a, int b, int p) {
        this.treeHandler = new TreeHandler();
        this.tableHandler = new TableHandler(a, b , p);
        this.dataHandler = new DataHandler(treeHandler, tableHandler);
    }

    public Response HandleRequest(Request request) {
        Response response = new Response(null);
        switch (request.getRequestType()) {
            case ADDS:
                int studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                String name = getData(request.getRequest(), 2);
                dataHandler.addStudent(studentCode, name);
            case ADDC:
                int courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.addCourse(courseCode, name);
            case ADDG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                int semester = Integer.parseInt(getData(request.getRequest(), 3));
                double grade = Double.parseDouble(getData(request.getRequest(), 4));
                dataHandler.addGrade(studentCode, courseCode, semester, grade);
            case EDITS:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.editStudent(studentCode, name);
            case EDITC:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.editCourse(courseCode, name);
            case EDITG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                grade = Double.parseDouble(getData(request.getRequest(), 3));
                dataHandler.editGrade(studentCode, courseCode, grade);
            case DELETES:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                dataHandler.deleteStudent(studentCode);
            case DELETEC:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                dataHandler.deleteCourse(courseCode);
            case DELETEG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                dataHandler.deleteGrade(studentCode, courseCode);
            case NUMBERC:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(String.valueOf(dataHandler.getNumberC(studentCode)));
            case NUMBERS:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(String.valueOf(dataHandler.getNumberS(courseCode)));
            default:
                response = handleRequest2(request);
        }
        return response;
    }

    private Response handleRequest2(Request request) {
        Response response = new Response(null);
        switch (request.getRequestType()) {
            case SEARCHSN:
                String name = getData(request.getRequest(), 1);
                response.setResponse(treeHandler.findStudentData(name));
            case SEARCHCN:
                name = getData(request.getRequest(), 1);
                response.setResponse(treeHandler.findCourseData(name));
            case SEARCHSC:
                int code = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(tableHandler.findStudentData(code));
            case SEARCHCC:
                code = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(tableHandler.findCourseData(code));
        }
        return response;
    }

    private String getData(String request, int n) {
        String[] s = request.split(" ");
        return s[n].substring(1, s[n].length() - 1);
    }
}
