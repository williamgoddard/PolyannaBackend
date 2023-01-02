package me.wgoddard.PolyannaBackend.response;

public class Response {

    private Object data;
    private String response;
    private boolean success;

    public Response(Object data, String response, boolean success) {
        this.data = data;
        this.response = response;
        this.success = success;
    }

}
