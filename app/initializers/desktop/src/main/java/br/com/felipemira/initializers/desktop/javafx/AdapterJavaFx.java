package br.com.felipemira.initializers.desktop.javafx;

import br.com.felipemira.common.util.PropertiesUtils;
import br.com.felipemira.initializers.desktop.javafx.screen.TransferForm;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;


// Responsavel por fazer o ponto de inicio de execucao
public class AdapterJavaFx extends Application {
    private ApplicationContext spring;

    @Override
    public void init() throws ClassNotFoundException {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        String build = (String) PropertiesUtils.loadProperties(args).get("build");
        Class<?> classBuild = Class.forName(build);

        this.spring = new SpringApplicationBuilder()
                .sources(classBuild)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        var form = spring.getBean(TransferForm.class);
        form.show(stage);
    }

}

