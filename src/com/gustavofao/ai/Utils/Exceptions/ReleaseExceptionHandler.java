package com.gustavofao.ai.Utils.Exceptions;

import com.gustavofao.ai.Utils.ExceptionHandler;

public class ReleaseExceptionHandler implements ExceptionHandler {
    @Override
    public void handleException(Throwable t) {
        System.out.printf("New exception intercepted: %s\n", t.getMessage());
    }
}
