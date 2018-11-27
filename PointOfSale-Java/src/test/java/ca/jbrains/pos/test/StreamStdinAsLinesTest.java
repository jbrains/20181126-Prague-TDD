package ca.jbrains.pos.test;

import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class StreamStdinAsLinesTest {
    @Test
    public void noText() throws Exception {
        Assert.assertEquals(Stream.empty(), streamAsLines(new StringReader("")));
    }

    @Test
    public void oneLineWithoutALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line::"),
                streamAsLines(new StringReader("::line::")));
    }

    private Stream<String> streamAsLines(Reader source) {
        try {
            final String line = new BufferedReader(source).readLine();
            return line == null ? Stream.empty() : Stream.of(line);
        } catch (IOException e) {
            throw new RuntimeException("Reading commands from a Reader failed somehow.", e);
        }
    }
}
