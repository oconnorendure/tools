package net.itxiu.response;

public class ResponseEntity<T> {
    private Integer code = ResponseCodeEnum.SUCCESS.getCode();
    private String message = ResponseCodeEnum.SUCCESS.getMessage();

    T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean success() {
        return code != null && code.equals(ResponseCodeEnum.SUCCESS.getCode());
    }
}
