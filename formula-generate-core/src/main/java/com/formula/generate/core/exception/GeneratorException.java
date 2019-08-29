package com.formula.generate.core.exception;

/**
 * @author:luyanan
 * @email:luyanan0718@163.com
 * @date 19-3-8
 * @introduce 自定义异常
 **/
public class GeneratorException extends RuntimeException {
    private String message;

    public GeneratorException(String message) {
        super(message);
        this.message = message;
    }


    public GeneratorException() {
        super();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
