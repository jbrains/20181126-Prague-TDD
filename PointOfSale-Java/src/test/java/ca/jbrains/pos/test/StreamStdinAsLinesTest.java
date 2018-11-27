package ca.jbrains.pos.test;

import io.vavr.collection.List;
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

    @Test
    public void oneLineEndingInALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line::"),
                streamAsLines(new StringReader(toMultilineText(List.of("::line::")))));
    }

    @Test
    public void justALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of(""),
                streamAsLines(new StringReader(System.lineSeparator())));
    }

    @Test
    public void severalLinesWithATrailingLineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line 1::", "::line 2::", "::line 3::"),
                streamAsLines(new StringReader(toMultilineText(List.of("::line 1::", "::line 2::", "::line 3::")))));

    }

    private Stream<String> streamAsLines(Reader source) {
        return Stream.ofAll(new BufferedReader(source).lines());
    }

    // CONTRACT Adds a trailing line separator to the entire "document".
    // REFACTOR Move me to some extension class for StringBuilder. Maybe DocumentBuilder?
    private static String toMultilineText(final List<String> lines) {
        return lines.foldLeft(
                new StringBuilder(),
                (sb, line) -> sb.append(line).append(System.lineSeparator())
        ).toString();
    }
}
