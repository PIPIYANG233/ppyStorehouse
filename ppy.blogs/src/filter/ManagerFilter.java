package filter;

import domain.Manager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/manager_jsp/Manager.jsp")
public class ManagerFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Manager manager =null;
        manager = (Manager)((HttpServletRequest) req).getSession().getAttribute("manager");
        if(null == manager){
            ((HttpServletResponse) resp).sendRedirect("/main_jsp/HOME.jsp");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
