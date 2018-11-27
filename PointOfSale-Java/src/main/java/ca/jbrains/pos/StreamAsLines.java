package ca.jbrains.pos;

import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.Reader;

public class StreamAsLines {
    public Stream<String> streamAsLines(Reader source) {
        return Stream.ofAll(new BufferedReader(source).lines());
    }
}