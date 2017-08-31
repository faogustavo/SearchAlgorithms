package com.gustavofao.ai.Utils.Exceptions;

import com.gustavofao.ai.Utils.ExceptionHandler;

public class DebugExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Throwable t) {
        t.printStackTrace();
    }

}
