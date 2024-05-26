package br.com.felipemira.initializers.builds.dois;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// Responsavel por configurar os servicos do spring
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(value = {
        // adptadores front-end javafx
        "br.com.felipemira.initializers.desktop.javafx",
        // core do sistema
        "br.com.felipemira.application.core",
        // adaptadores front-end web
        "br.com.felipemira.web",
        // adptadores falsos
        "br.com.felipemira.application.adapters.fake"},
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "br\\.com\\.felipemira\\.web\\.out.*")
})
public class BuildDois {
    // Build Dois - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
