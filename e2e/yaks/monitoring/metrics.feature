Feature: Camel K can run latest released Knative CamelSource

  Background: Prepare Thanos URL
    Given URL: https://thanos-querier.openshift-monitoring:9091
    And HTTP request header Authorization is "Bearer TOKEN"
    And sleep 4000000 ms
    
  Scenario: Integration gets the message from the timer
    Given integration metrics is running
    Then integration metrics should print Successfully processed

  Scenario: Thanos is able to receive metrics
    When send GET /api/v1/query?query=application_attempt_total
    And receive HTTP 200
    And expect HTTP response body: OK
