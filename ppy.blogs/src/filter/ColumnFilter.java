package filter;

import domain.ColumnManagerPage;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user_jsp/USER_managerColumns.jsp")
public class ColumnFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ColumnManagerPage columnManagerPage =null;
        columnManagerPage = (ColumnManagerPage)((HttpServletRequest) req).getSession().getAttribute("columnManagerPage");
        if(null == columnManagerPage){
            ((HttpServletResponse) resp).sendRedirect("/main_jsp/HOME.jsp");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
