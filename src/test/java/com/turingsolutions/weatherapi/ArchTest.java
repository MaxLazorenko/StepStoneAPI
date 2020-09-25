package com.turingsolutions.weatherapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.turingsolutions.weatherapi");

        noClasses()
            .that()
                .resideInAnyPackage("com.turingsolutions.weatherapi.service..")
            .or()
                .resideInAnyPackage("com.turingsolutions.weatherapi.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.turingsolutions.weatherapi.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
