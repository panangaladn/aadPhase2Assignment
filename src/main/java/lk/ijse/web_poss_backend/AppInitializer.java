package lk.ijse.web_poss_backend;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lk.ijse.web_poss_backend.config.WebAppConfig;
import lk.ijse.web_poss_backend.config.WebAppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String tempDir = System.getProperty("java.io.tmpdir"); //windows operating system
        registration.setMultipartConfig(new MultipartConfigElement(tempDir));
    }
}
