package com.elDome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DomeService {

    @Value("其他属性")
    private String oanther;

    public String getOanther() {
        return oanther;
    }

    public void setOanther(String oanther) {
        this.oanther = oanther;
    }
}
