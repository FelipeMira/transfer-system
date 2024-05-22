package br.com.felipemira.initializers.builds.tresquatro;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({// adptadores front-end javafx
        "conta.javafx",
        // adptadores reais
        "conta.servicos.config"})
public class BuildTresEQuatro {

}
