package com.example.designpatternspider.selenium.exception;

import org.apache.logging.log4j.message.Message;

public class MyException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -8307292506718315410L;

    
    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

}
