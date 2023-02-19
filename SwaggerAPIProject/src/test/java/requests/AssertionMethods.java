package requests;


public class AssertionMethods {

    public Requests requests;
    String status;

    public AssertionMethods() {
        requests = new Requests();
    }

    public int checkStatusCode() {
        return requests.response.statusCode();
    }
    public String checkPath(String path) {
        status = requests.response.path(path).toString();
        return status;

    }
}
