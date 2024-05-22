package br.com.felipemira.initializers.builds.dois;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsavel por configurar os servicos do spring
@Configuration
@ComponentScan({
        // adptadores front-end javafx
        "br.com.felipemira.initializers.desktop.javafx",
        // core do sistema
        "br.com.felipemira.application.core",
        // adptadores falsos
        "br.com.felipemira.application.adapters.fake"})
public class BuildDois {
    // Build Dois - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
