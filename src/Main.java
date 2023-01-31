import request_response.Request;
import request_response.RequestHandler;
import request_response.RequestType;
import request_response.Response;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        int p = Integer.parseInt(scanner.nextLine());
        RequestHandler handler = new RequestHandler(a, b, p);
        for (int i = 0; i < T; i++) {
            String requestText = scanner.nextLine();
            Request request = new Request(RequestType.valueOf
                    (requestText.split(" ")[0]), requestText);
            Response response = handler.HandleRequest(request);
            if (response.getResponse() != null)
                System.out.println(response.getResponse());
        }
    }
}
