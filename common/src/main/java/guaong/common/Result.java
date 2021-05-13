package guaong.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private boolean success = true;

    private String msg;

    private int status;

    private T result;

    public static<T> Result<T> send(int status, boolean success, String msg, T result){
        Result<T> r = new Result<>();
        r.setMsg(msg);
        r.setStatus(status);
        r.setSuccess(success);
        r.setResult(result);
        return r;
    }

    public static<T> Result<T> OK(int status, String msg, T result){
        return send(status, true, msg, result);
    }

    public static<T> Result<T> OK(String msg, T result){
        return OK(200, msg, result);
    }

    public static<T> Result<T> OK(T result){
        return OK("成功", result);
    }

    public static<T> Result<T> OK(){
        return OK(null);
    }



    public static<T> Result<T> ERROR(int status, String msg, T result){
        return send(status, false, msg, result);
    }

    public static<T> Result<T> ERROR(String msg, T result){
        return ERROR(500, msg, result);
    }

    public static<T> Result<T> ERROR(T result){
        return ERROR("失败", result);
    }

    public static<T> Result<T> ERROR(){
        return ERROR(null);
    }


}
