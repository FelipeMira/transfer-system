package br.com.felipemira.initializers.test;

import br.com.felipemira.common.util.PropertiesUtils;
import br.com.felipemira.initializers.desktop.javafx.screen.TransferForm;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
public class JavaFxTest {

    private ConfigurableApplicationContext spring;

    @Start
    public void start(Stage stage) throws ClassNotFoundException {
        System.getProperties().setProperty("spring.profiles.active", "test");
        String[] args = new String[0];
        String build = (String) PropertiesUtils.loadProperties(args).get("build");
        Class<?> classBuild = Class.forName(build);

        spring = new SpringApplicationBuilder()
                .sources(classBuild)
                .run(args);
        var form = spring.getBean(TransferForm.class);
        form.show(stage);
    }

    @AfterEach
    public void tearDown() {
        if (spring != null) {
            spring.close();
        }
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void validarTransferencia(FxRobot robot){
        robot.clickOn("#tfDebit");
        robot.write("1");

        robot.clickOn("#tfCredit");
        robot.write("2");

        robot.clickOn("#tfBalance");
        robot.write("20.00", 5);

        robot.clickOn("#bt");

        Node dialogPane = robot.lookup("#alert").query();
        Assertions.assertEquals(((DialogPane) dialogPane).getContentText(), "Transferencia feita com sucesso!");

        robot.clickOn("OK");

        // Finaliza a aplicacao
        //Platform.runLater(Platform::exit);

        //verifyThat("Transferencia feita com sucesso!", NodeMatchers.isVisible());
    }

}
