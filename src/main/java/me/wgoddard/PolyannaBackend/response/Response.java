package me.wgoddard.PolyannaBackend.response;

public class Response<T> {

    public T data;
    public String response;
    public boolean success;

    public Response(T data, String response, boolean success) {
        this.data = data;
        this.response = response;
        this.success = success;
    }

}
