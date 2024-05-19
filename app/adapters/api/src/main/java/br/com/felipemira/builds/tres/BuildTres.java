package br.com.felipemira.builds.tres;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableFeignClients(basePackages = {"conta.adaptadores.interfaces"})
@EnableCaching
@ComponentScan({// adptadores front
        "conta.api",
        // adptadores reais
        "conta.servicos.config",
        "conta.adaptadores"})
public class BuildTres {

}
