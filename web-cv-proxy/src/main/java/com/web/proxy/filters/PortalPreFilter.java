package com.web.proxy.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortalPreFilter /**extends ZuulFilter**/ {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }

//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest req = ctx.getRequest();
//
//        String URI = req.getRequestURI().toString();
//        String methodType = req.getMethod();
//        LOG.info("{} Method to {}", methodType, URI);
//        return null;
//    }
}
