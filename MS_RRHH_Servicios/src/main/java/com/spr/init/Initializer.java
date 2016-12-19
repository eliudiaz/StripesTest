package com.spr.init;

import gt.org.ms.web.NativeCorsFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String GLASSFISH_PROFILE_PARAM = "rrhh_profile";
    private static final String SPRING_PROFILE_PARAM = "spring.profiles.active";

    @Override
    public void onStartup(ServletContext container)
            throws ServletException {
        String profile = System.getProperty(GLASSFISH_PROFILE_PARAM);
        profile = profile != null
                ? profile : "prod";
        System.out.println(">>> active profile: " + profile);
        container.setInitParameter(SPRING_PROFILE_PARAM, profile);

        AnnotationConfigWebApplicationContext ctx
                = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        container.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(container);
        container.addFilter("CorsFilter", NativeCorsFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");
        Dynamic servlet = container.addServlet(DISPATCHER_SERVLET_NAME,
                new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
