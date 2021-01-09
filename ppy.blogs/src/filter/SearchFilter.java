package filter;

import domain.Search;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/main_jsp/Search.jsp")
public class SearchFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Search search =null;
        search = (Search)((HttpServletRequest) req).getSession().getAttribute("search");
        if(null == search){
            ((HttpServletResponse) resp).sendRedirect("/main_jsp/HOME.jsp");
        }else{
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
