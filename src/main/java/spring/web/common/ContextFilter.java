package spring.web.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="contextFilter",urlPatterns="/*")
public class ContextFilter implements Filter {

    private static final Log log = LogFactory.getLog(ContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContextThread.buildContext((HttpServletRequest) request, (HttpServletResponse) response);
        log.info("Url: " + ((HttpServletRequest) request).getRequestURI());
        log.info("Headers: " + ContextThread.getHeaderParam());
        log.info("Params: " + ContextThread.getRequestParam());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
