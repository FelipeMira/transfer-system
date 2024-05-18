package br.com.felipemira.adapters.database.test;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SpringBootTest
@SelectPackages({"br.com.felipemira.adapters.database.test.integracao"})
public class SuiteIntegracaoTest {
    // 100% da integracao com servicos externos testados.
}
