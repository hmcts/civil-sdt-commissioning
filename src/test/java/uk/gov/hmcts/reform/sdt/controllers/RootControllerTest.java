package uk.gov.hmcts.reform.sdt.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RootControllerTest {

    @Test
    void welcome() {
        RootController rootController = new RootController();
        ResponseEntity<String> responseEntity = rootController.welcome();
        assertEquals("200 OK", responseEntity.getStatusCode().toString(), "200 status not achieved");
    }
}
