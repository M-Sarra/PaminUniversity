package request_response;

import controller.DataHandler;
import controller.TableHandler;
import controller.TreeHandler;

public class RequestHandler {
    private final DataHandler dataHandler;
    private final TreeHandler treeHandler;
    private final TableHandler tableHandler;

    public RequestHandler(int a, int b, int p) {
        boolean isCreating = p != 1;
        this.treeHandler = new TreeHandler();
        this.tableHandler = new TableHandler(a, b , p);
        this.dataHandler = new DataHandler(treeHandler, tableHandler, isCreating);
    }

    public Response HandleRequest(Request request) {
        Response response = new Response(null);
        switch (request.getRequestType()) {
            case ADDS:
                int studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                String name = getData(request.getRequest(), 2);
                dataHandler.addStudent(studentCode, name);
                break;
            case ADDC:
                int courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.addCourse(courseCode, name);
                break;
            case ADDG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                int semester = Integer.parseInt(getData(request.getRequest(), 3));
                double grade = Double.parseDouble(getData(request.getRequest(), 4));
                dataHandler.addGrade(studentCode, courseCode, semester, grade);
                break;
            case EDITS:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.editStudent(studentCode, name);
                break;
            case EDITC:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                name = getData(request.getRequest(), 2);
                dataHandler.editCourse(courseCode, name);
                break;
            case EDITG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                grade = Double.parseDouble(getData(request.getRequest(), 3));
                dataHandler.editGrade(studentCode, courseCode, grade);
                break;
            case DELETES:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                dataHandler.deleteStudent(studentCode);
                break;
            case DELETEC:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                dataHandler.deleteCourse(courseCode);
                break;
            case DELETEG:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                courseCode = Integer.parseInt(getData(request.getRequest(), 2));
                dataHandler.deleteGrade(studentCode, courseCode);
                break;
            case NUMBERC:
                studentCode = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(String.valueOf(dataHandler.getNumberC(studentCode)));
                break;
            case NUMBERS:
                courseCode = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(String.valueOf(dataHandler.getNumberS(courseCode)));
                break;
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
                break;
            case SEARCHCN:
                name = getData(request.getRequest(), 1);
                response.setResponse(treeHandler.findCourseData(name));
                break;
            case SEARCHSC:
                int code = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(tableHandler.findStudentData(code));
                break;
            case SEARCHCC:
                code = Integer.parseInt(getData(request.getRequest(), 1));
                response.setResponse(tableHandler.findCourseData(code));
                break;
        }
        return response;
    }

    private String getData(String request, int n) {
        String[] s = request.split(" ");
        return s[n].substring(1, s[n].length() - 1);
    }
}
