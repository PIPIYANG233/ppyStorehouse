package filter;

import domain.CheckBlog;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/createspace_jsp/Blog.jsp")
public class BlogFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        CheckBlog checkBlog =null;
        checkBlog = (CheckBlog)((HttpServletRequest) req).getSession().getAttribute("checkBlog");
        if(null == checkBlog){
            ((HttpServletResponse) resp).sendRedirect("/main_jsp/HOME.jsp");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
