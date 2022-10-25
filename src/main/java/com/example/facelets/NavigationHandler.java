package com.example.facelets;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NavigationHandler {
    public String goIndex() {
        return "go-index";
    }
    public String goStart() {
        return "go-start";
    }
}
