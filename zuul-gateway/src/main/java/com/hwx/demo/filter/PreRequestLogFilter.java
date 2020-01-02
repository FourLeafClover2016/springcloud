package com.hwx.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul pre过滤器
 */
@Configuration
public class PreRequestLogFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreRequestLogFilter.class);

    @Override
    public String filterType() {
        //PRE 类型过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        LOGGER.info("send " + request.getMethod() + "  " + request.getRequestURI());
        return null;
    }
}
