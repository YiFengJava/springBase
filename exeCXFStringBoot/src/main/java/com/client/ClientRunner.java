package com.client;

import java.util.Collections;

public class ClientRunner {

    public static void main(String[] args) {
        HelloProxyController proxy = ControllerProxyFactory.createControllerProxy(HelloProxyController.class,
                "http://localhost:8080", Collections.emptyMap());

        String message = proxy.hello("WangJunchao");

        System.out.println(message);
    }
}


