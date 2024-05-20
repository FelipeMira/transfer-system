package br.com.felipemira.builds.dois;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsavel por configurar os servicos do spring
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({
        // adaptadores front
        "br.com.felipemira.web",
        // core do sistema
        "br.com.felipemira.application.core",
        // adptadores falsos
        "br.com.felipemira.application.adapters.fake"})
public class BuildDois {
    // Build Dois - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
