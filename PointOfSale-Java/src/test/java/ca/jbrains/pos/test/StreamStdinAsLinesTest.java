package ca.jbrains.pos.test;

import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

public class StreamStdinAsLinesTest {
    @Test
    public void noText() throws Exception {
        Assert.assertEquals(Stream.empty(), streamAsLines(new StringReader("")));
    }

    private Stream<String> streamAsLines(Reader source) {
        return Stream.empty();
    }
}
