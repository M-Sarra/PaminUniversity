package request_response;

import controller.DataHandler;

public class RequestHandler {

    public Response HandleRequest(Request request) {
        DataHandler dataHandler = new DataHandler();
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
        }
        return response;
    }

    private String getData(String request, int n) {
        String[] s = request.split(" ");
        return s[n].substring(1, s[n].length() - 1);
    }
}
