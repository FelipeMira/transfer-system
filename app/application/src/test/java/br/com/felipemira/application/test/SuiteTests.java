package br.com.felipemira.application.test;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({
        "br.com.felipemira.application.test.core.domain",
        "br.com.felipemira.application.test.core.exceptions",
        "br.com.felipemira.application.test.core.usecase",
        "br.com.felipemira.application.test.core.validation"})
@Suite
public class SuiteTests {
}
