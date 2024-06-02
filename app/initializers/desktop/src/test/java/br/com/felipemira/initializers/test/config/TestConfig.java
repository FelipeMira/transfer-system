package br.com.felipemira.initializers.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(value = {
        // adptadores front-end javafx
        "br.com.felipemira.initializers.desktop.javafx",
        // core do sistema
        "br.com.felipemira.application.core",
        // adptadores falsos
        "br.com.felipemira.application.adapters.fake"},
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "br\\.com\\.felipemira\\.web\\.out.*")
})
public class TestConfig {

}
