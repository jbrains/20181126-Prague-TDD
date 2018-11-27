package ca.jbrains.pos.test;

import ca.jbrains.pos.StreamAsLines;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

public class StreamStdinAsLinesTest {
    private final StreamAsLines streamAsLines = new StreamAsLines();

    @Test
    public void noText() throws Exception {
        Assert.assertEquals(Stream.empty(), streamAsLines.streamAsLines(new StringReader("")));
    }

    @Test
    public void oneLineWithoutALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line::"),
                streamAsLines.streamAsLines(new StringReader("::line::")));
    }

    @Test
    public void oneLineEndingInALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line::"),
                streamAsLines.streamAsLines(new StringReader(toMultilineText(List.of("::line::")))));
    }

    @Test
    public void justALineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of(""),
                streamAsLines.streamAsLines(new StringReader(System.lineSeparator())));
    }

    @Test
    public void severalLinesWithATrailingLineSeparator() throws Exception {
        Assert.assertEquals(
                Stream.of("::line 1::", "::line 2::", "::line 3::"),
                streamAsLines.streamAsLines(new StringReader(toMultilineText(List.of("::line 1::", "::line 2::", "::line 3::")))));

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
