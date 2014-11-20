package com.contentbowl.commons.tenant;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NamespaceFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doFilterInternal((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	/*
    	String tenantId = tenantDetectionStrategy.resolveTenantFrom(request);
    	try {
            Tenant tenant = tenantRepository.get(tenantId);
            String tenantNamespace = tenant.namespace();
            request.setAttribute("tenantId", tenantId);
            String oldNamespace = NamespaceManager.get();
            logger.finer("Backing up old namespace: " + oldNamespace);
            logger.finer("Setting namespace to: " + tenantNamespace);
            NamespaceManager.set(tenantNamespace);
            TenantContextUtils.set(new TenantContext(tenant));
            try {
                filterChain.doFilter(request, response);
            } finally {
                logger.finer("Setting namespace back to: " + oldNamespace);
                NamespaceManager.set(oldNamespace);
                TenantContextUtils.unset();
            }
        } catch (TenantNotFoundException | TenantInactiveException e) {
            logger.warning(String.format("Tenant %s not found or inactive. Rejecting request.", tenantId));
            response.sendError(HttpServletResponse.SC_NOT_FOUND);;
        }
        */
    }
    
    @Override
    public void destroy() {
    }
}
