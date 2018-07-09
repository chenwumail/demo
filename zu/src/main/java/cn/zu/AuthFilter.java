package cn.zu;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends ZuulFilter {
  private Logger log = LoggerFactory.getLogger(AuthFilter.class);
  @Override
  public String filterType() {
    return "pre";
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
    RequestContext requestContext = RequestContext.getCurrentContext();
    requestContext.addZuulRequestHeader("X-User-Id", "20009999");
    log.info("add X-User-Id header for request.");
    return null;
  }
}
