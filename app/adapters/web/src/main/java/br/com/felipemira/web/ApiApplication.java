package br.com.felipemira.web;

import br.com.felipemira.common.util.PropertiesUtils;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        String build = (String) PropertiesUtils.loadProperties().get("build");
        Class<?> classBuild = Class.forName(build);

        new SpringApplicationBuilder()
                .web(WebApplicationType.SERVLET)
                .sources(classBuild)
                .run(args);
    }

}
