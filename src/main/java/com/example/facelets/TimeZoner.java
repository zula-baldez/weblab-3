package com.example.facelets;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.Objects;


@ApplicationScoped
@ManagedBean
public class TimeZoner {
    private String timeZone;
    public String getTimeZone() {
        return timeZone;


    }
    public void setTimeZone() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.timeZone =  params.get("timezone");


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeZoner timeZoner = (TimeZoner) o;
        return Objects.equals(timeZone, timeZoner.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeZone);
    }
}
