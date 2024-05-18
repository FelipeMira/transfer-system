package br.com.felipemira.transfer;


import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@SelectPackages({
        "br.com.felipemira.transfer.domain",
        "br.com.felipemira.transfer.exceptions",
        "br.com.felipemira.transfer.usecase",
        "br.com.felipemira.transfer.validation"})
@Suite
public class SuiteTests {
}
