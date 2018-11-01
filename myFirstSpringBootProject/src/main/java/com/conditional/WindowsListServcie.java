package com.conditional;

public class WindowsListServcie implements  ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
