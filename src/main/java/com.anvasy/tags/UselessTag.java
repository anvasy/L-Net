package com.anvasy.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class UselessTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String copyright = "<footer><h5>Copyright © 2019 Ann.Vasilyeva</h5> </footer> ";
        try {
            pageContext.getOut().write(copyright);
        } catch (IOException e) {
            throw new JspException(e.getMessage()); }
        return SKIP_BODY;
    }

    @Override
    public int
    doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
