package m01_streams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty("city")
    private String name;
    private String country;
    private Long id;

    @Setter(AccessLevel.NONE)
    private int population;

    @JsonProperty("population")
    public void setPopulationFromCsv(String raw) {
        if (raw == null || raw.isBlank()) { this.population = 0; return; }
        String s = raw.replace(",", "").trim();
        this.population = new BigDecimal(s).setScale(0, RoundingMode.DOWN).intValueExact();
    }
}