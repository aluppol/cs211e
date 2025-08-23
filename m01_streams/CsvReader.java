import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public final class CsvReader {
    private static final CsvMapper CSV = CsvMapper.builder()
        .enable(CsvParser.Feature.TRIM_SPACES)
        .build();
}
