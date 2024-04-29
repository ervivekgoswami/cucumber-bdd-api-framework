package com.api.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		
plugin = {"pretty","html:target/cucumber/report.html", "json:target/cucumber/cucumber.json" }, 
features = { "src/test/resources/features" }, 
glue = {"com.api.stepdefinition" }, 
monochrome = true, 
snippets = SnippetType.CAMELCASE, 
tags = "@Price"
// ,publish = true
)
public class TestRunner {

}