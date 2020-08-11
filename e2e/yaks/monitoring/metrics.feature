Feature: Camel K can run latest released Knative CamelSource

  Background: Prepare Thanos URL
    Given URL: https://thanos-querier.openshift-monitoring:9091
    And HTTP request header Authorization is "Bearer TOKEN"
    
  Scenario: Integration gets the message from the timer
    Given integration metrics is running
    Then integration metrics should print Successfully processed

  Scenario: Thanos is able to receive metrics
    Then say hello
    Then wait for path /api/v1/query?query=application_attempt_total to return 200 with body matching .*result.*
