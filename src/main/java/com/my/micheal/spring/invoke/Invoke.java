package com.my.micheal.spring.invoke;


public interface Invoke <T>{

    T invoke(Invocation invocation) throws Exception;

}
