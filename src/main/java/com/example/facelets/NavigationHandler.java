package com.example.facelets;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class NavigationHandler implements Serializable {
    public String goIndex() {
        return "go-index";
    }
    public String goStart() {
        return "go-start";
    }
}
