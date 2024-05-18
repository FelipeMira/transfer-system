package br.com.felipemira.transfer.usecase.builds;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsavel por configurar os servicos do spring
@Configuration
// objetos de sistema// adptadores falsos
@ComponentScan({"br.com.felipemira.transfer.adapters.fake","br.com.felipemira.transfer.application"})
public class BuildOne {

}
