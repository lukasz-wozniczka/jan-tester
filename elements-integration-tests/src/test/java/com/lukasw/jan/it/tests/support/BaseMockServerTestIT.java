package com.lukasw.jan.it.tests.support;

import org.mockserver.client.MockServerClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base integration test class to use {@link MockServerClient} and mange it lifecycle.
 */
public class BaseMockServerTestIT {

    private MockServerClient server;

    /**
     * Return the {@link MockServerClient}.
     *
     * @return mock server client
     */
    protected final MockServerClient mockServerClient() {
        return this.server;
    }

    @BeforeClass
    protected final void beforeClass() {
        this.server = MockServerClientHolder.getInstance();
    }

    @AfterClass
    protected final void afterClass() {
        //If running locally ensure that server is stopped
        MockServerClientHolder.closeIfLocal();
    }
}
