package request_response;

public class Request {
    private final RequestType requestType;
    private final String request;

    public Request(RequestType requestType, String request) {
        this.requestType = requestType;
        this.request = request;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getRequest() {
        return request;
    }
}
