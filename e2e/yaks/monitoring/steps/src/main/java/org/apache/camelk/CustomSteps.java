package org.apache.camelk;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import io.cucumber.java.en.Then;

import static com.consol.citrus.container.RepeatOnErrorUntilTrue.Builder.repeatOnError;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import org.springframework.http.HttpStatus;

public class CustomSteps {

    @CitrusResource
    private TestCaseRunner runner;

    @Then("^say hello$")
    public void sayHello(){
        System.out.println("Helllllooooo");
    }

    @Then("^wait for (?:URL|url|path) ([^\\s]+) to return (\\d+) with body matching ([^\\s]+)$")
    public void verifyUrlWithBodyPatternMatcher(String urlOrPath, Integer statusCode, String pattern) {
        runner.then(repeatOnError()
                .autoSleep(5000)
                .until((i, context) -> i == 100)
                .actions(
                        http().client(urlOrPath)
                                .send()
                                .get(),
                        http().client(urlOrPath)
                                .receive()
                                .response(HttpStatus.valueOf(statusCode))
                                .payload(String.format("@matches(%s)@", pattern))));
    }

}
