package com.epam.Per1.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.util.Calendar;

public class MyTag extends SimpleTagSupport{
    public void doTag() throws JspException {
        JspWriter out=getJspContext().getOut();//returns the instance of JspWriter
        try{
            out.print(Calendar.getInstance().getTime());//printing date and time using JspWriter
        }catch(Exception e){System.out.println(e);}
    }
}
