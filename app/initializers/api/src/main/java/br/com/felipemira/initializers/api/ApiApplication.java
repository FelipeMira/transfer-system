package br.com.felipemira.initializers.api;

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
                .sources(classBuild)
                .run(args);
    }

}
