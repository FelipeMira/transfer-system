package br.com.felipemira.initializers.builds.tresQuatro;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableFeignClients(basePackages = {"br.com.felipemira.web.out.feing.clients", "br.com.felipemira.web.out.feing.controller"})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableCaching
@ComponentScan({// adptadores front
        "br.com.felipemira.web",
        "br.com.felipemira.initializers.desktop.javafx",
        // adptadores reais
        "br.com.felipemira.database.config",
        "br.com.felipemira.application.core"})
public class BuildTresQuatro {

}
