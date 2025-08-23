package m01_streams;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public final class CsvReader {
    private static final CsvMapper CSV = CsvMapper.builder()
            .enable(CsvParser.Feature.TRIM_SPACES)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    public static <T>Stream<T> streamCsv(Path csvPath, Class<T> type) throws IOException {
        Objects.requireNonNull(csvPath, "csvPath");
        Objects.requireNonNull(type, "type");

        final CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withQuoteChar('"')
                .withEscapeChar('\\');

        final BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8);

        try {
            final MappingIterator<T> mi = CSV.readerFor(type)
                    .with(schema)
                    .readValues(reader);

            final Stream<T> stream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(mi, Spliterator.ORDERED),
                    false
            );

            return stream.onClose(() -> {
                try { mi.close(); } catch (IOException e) { throw new RuntimeException(e); }
                try { reader.close(); } catch (IOException e) { throw new RuntimeException(e); }
            });
        } catch (IOException | RuntimeException e) {
            try { reader.close(); } catch (IOException suppressed) { e.addSuppressed(suppressed); }
            throw e;
        }
    }
}
