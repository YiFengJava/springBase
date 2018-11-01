package com.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;

import java.util.Map;

public class ControllerProxyFactory {


    public static <T> T createControllerProxy(Class<T> clazz, String host, Map<String, String> headers) {
        JAXRSClientFactoryBean factoryBean = new JAXRSClientFactoryBean();
        factoryBean.setAddress(host);
        factoryBean.setHeaders(headers);
        factoryBean.setInheritHeaders(true);
        factoryBean.setServiceClass(clazz);
        factoryBean.setProvider(new JacksonJsonProvider());
        return (T) factoryBean.create();
    }
}
