package br.com.felipemira.common.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface PersistenceAdapter {

    /**
     * O valor pode indicar uma sugestão para um nome de componente lógico,
     * para ser transformado em um bean Jakarta no caso de um componente detectado automaticamente.
     * @return o nome sugerido do componente, se houver (ou String vazia caso contrário)
     */
    @AliasFor(annotation = Component.class)
    String value() default "";
}
