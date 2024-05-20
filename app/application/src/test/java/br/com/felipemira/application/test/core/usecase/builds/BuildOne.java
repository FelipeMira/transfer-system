package br.com.felipemira.application.test.core.usecase.builds;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsavel por configurar os servicos do spring
@Configuration
// objetos de sistema// adptadores falsos
@ComponentScan({"br.com.felipemira.application.adapters.fake","br.com.felipemira.application.core"})
public class BuildOne {

}
