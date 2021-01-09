package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(uri.contains("/main_jsp/") || uri.contains("/createspace_jsp/Blog.jsp")
                || uri.contains("/css") || uri.contains("/fonts") || uri.contains("/img") || uri.contains("/js") || uri.contains("/user_head")
                || uri.contains("/editor.md-master") || uri.contains("ChangeCommentPage") || uri.contains("FindPassword")
                || uri.contains("ToAuthorSpace") || uri.contains("CheckMyself") || uri.contains("CheckOtherUserBlogs")
                || uri.contains("checkCode") || uri.contains("CheckLogin") || uri.contains("CheckRegister") || uri.contains("GetCheckCode") || uri.contains("UpdateUserPassword")
                || uri.contains("CheckHome") || uri.contains("CheckHomeBlog") || uri.contains("CheckSearch")){
            chain.doFilter(req, resp);
        }else{
            if(null != request.getSession().getAttribute("user")){
                chain.doFilter(req, resp);
            }else{
                ((HttpServletResponse) resp).sendRedirect("/main_jsp/LOGIN.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
