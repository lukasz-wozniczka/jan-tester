package com.lukasw.jan.support;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Helper class for tests to get HTML file from resources as a String.
 */
public final class ResourcesHtmlResponses {

    private static final String HTML_DIRECTORY = "/html";

    /**
     * Get HTML frile from resources by file name.
     *
     * @param htmlFile to get
     * @return file as a String
     * @throws RuntimeException if there are any issue to get file
     */
    public static String getHtmlFromResource(final HtmlFile htmlFile) {
        try {
            final URL resource = ResourcesHtmlResponses.class.getResource(htmlFile.path);
            final Path path = Paths.get(resource.toURI());
            return Files.lines(path).collect(Collectors.joining());
        } catch (final URISyntaxException | IOException e) {
            throw new RuntimeException("Exception while trying to get HTML file from the resource by path=" + htmlFile.path, e);
        }
    }

    public enum HtmlFile {
        TEST("test.html");

        private final String path;

        HtmlFile(final String fileName) {
            this.path = String.format("%s/%s", HTML_DIRECTORY, fileName);
        }
    }
}
