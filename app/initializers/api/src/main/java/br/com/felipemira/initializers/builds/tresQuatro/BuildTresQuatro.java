package br.com.felipemira.initializers.builds.tresQuatro;

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
        "br.com.felipemira.web",
        // adptadores reais
        "br.com.felipemira.database",
        "br.com.felipemira.application.core"})
public class BuildTresQuatro {

}
