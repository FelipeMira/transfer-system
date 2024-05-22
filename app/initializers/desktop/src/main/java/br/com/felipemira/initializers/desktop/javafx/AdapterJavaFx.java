package br.com.felipemira.initializers.desktop.javafx;

import br.com.felipemira.common.util.PropertiesUtils;
import br.com.felipemira.initializers.desktop.javafx.tela.TransferForm;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;


// Responsavel por fazer o ponto de inicio de execucao
public class AdapterJavaFx extends Application {
    private ApplicationContext spring;

    @Override
    public void init() throws ClassNotFoundException {
        String build = (String) PropertiesUtils.loadProperties().get("build");
        Class<?> classBuild = Class.forName(build);

        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.spring = new SpringApplicationBuilder()
                .sources(classBuild)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        var form = spring.getBean(TransferForm.class);
        form.mostrar(stage);
    }

}

