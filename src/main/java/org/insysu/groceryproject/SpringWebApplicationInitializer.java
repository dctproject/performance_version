package org.insysu.groceryproject;

import org.insysu.groceryproject.configuration.CacheConfig;
import org.insysu.groceryproject.configuration.JPAConfig;
import org.insysu.groceryproject.configuration.SecurityConfig;
import org.insysu.groceryproject.web.SpringWebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by buress on 12/3/16.
 */
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String CHARACTER_ENCODING = "UTF-8";


    public SpringWebApplicationInitializer() {
        super();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("shiroFilter", new DelegatingFilterProxy("shiroFilter"));
        encodingFilter.setInitParameter("targetFilterLifecycle", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringWebConfig.class };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { JPAConfig.class, SecurityConfig.class, CacheConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter};
    }

}
