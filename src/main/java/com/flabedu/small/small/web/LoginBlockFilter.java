package com.flabedu.small.small.web;

import lombok.val;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

//@Component
public class LoginBlockFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            var session = ((HttpServletRequest) request).getSession();
            String memberId = (String)session.getAttribute("MemberId");
            if(memberId != null){
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
