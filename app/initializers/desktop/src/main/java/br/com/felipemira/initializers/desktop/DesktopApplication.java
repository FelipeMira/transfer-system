package br.com.felipemira.initializers.desktop;

import br.com.felipemira.initializers.desktop.javafx.AdapterJavaFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesktopApplication {

    public static void main(String[] args) {
        Application.launch(AdapterJavaFx.class, args);
    }
}
