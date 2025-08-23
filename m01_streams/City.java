import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;



@Getter
@Setter
@AllArgsConstructor
public class City {
    @JsonProperty("city")
    private String name;
    private String country;
    private int population;
    private Long id;
}