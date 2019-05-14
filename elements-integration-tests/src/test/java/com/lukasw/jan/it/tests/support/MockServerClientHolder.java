package com.lukasw.jan.it.tests.support;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Provide lazy initialised singleton instance to hold instance of {@link MockServerClient}.
 * During initialisation check if {@link MockServerClient} is running, if yes then get this instance
 * otherwise is trying to start a new local instance by using {@link ClientAndServer#startClientAndServer(Integer...)}.
 *
 * <b>NOTE:</b> To ensure that the server is stopped once the tests are running locally (for example by IDE), call {@link MockServerClientHolder#closeIfLocal()} method after all tests finished.
 *
 * @see BaseMockServerTestIT
 */
public final class MockServerClientHolder {

    private static final Logger LOG = getLogger(MockServerClientHolder.class);
    private static final boolean LOCAL = false;

    private MockServerClient server;

    private MockServerClientHolder() {
        LOG.info("Create new instance of MockServerClientHolder");
        this.server = new MockServerClient("localhost", 1080);
        LOG.info("MockServer is running=" + this.server.isRunning());
        if (!this.server.isRunning()) {
            LOG.info("Start new MockServer on port=" + 1080);
            this.server = ClientAndServer.startClientAndServer(1080);
        } else {
            LOG.info("Attached existed MockServer instance");
        }
    }

    public static MockServerClient getInstance() {
        return LazyHolder.INSTANCE.server;
    }

    /**
     * Check whether {@link MockServerClient} is running locally if yes then stop instance.
     */
    public static void closeIfLocal() {
        LOG.info("Try closing MockServer. MockServer is running as local instance=" + LOCAL);
        if (LOCAL) {
            if (LazyHolder.INSTANCE.server.isRunning()) {
                LOG.info("Stopping local instance of MockServer.");
                LazyHolder.INSTANCE.server.stop();
            } else {
                LOG.info("MockServer is not running, nothing left to do ...");
            }
        }
    }

    private static class LazyHolder {
        private static final MockServerClientHolder INSTANCE = new MockServerClientHolder();
    }
}
