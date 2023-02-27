package com.api.ecommerce.bdd.runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.api.ecommerce.bdd"}
)
public class CucumberTestRunner {
}
