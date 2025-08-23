package m01_streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        classSample();

        try {
            List<City> smallCities = findCities(Paths.get("src", "main", "resources", "world_cities.csv"));
            smallCities.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Stream sample failed: " + e.toString());
        }
    }

    private static void classSample() {
        List<Product> productsList = new ArrayList<Product>();
        //Adding Products
        productsList.add(new Product(1,"HP Laptop",25000f));
        productsList.add(new Product(2,"Dell Laptop",30000f));
        productsList.add(new Product(3,"Lenevo Laptop",28000f));
        productsList.add(new Product(4,"Sony Laptop",28000f));
        productsList.add(new Product(5,"Apple Laptop",90000f));
        List<Float> productPriceList2 =productsList.stream()
                .filter(p -> p.price > 30000)
                .map(p->p.price)
                .collect(Collectors.toList());
        System.out.println(productPriceList2);
    }

    private static List<City> findCities(Path citiesCsvFile) throws IOException {
        if (!Files.exists(citiesCsvFile)) {
            throw new IllegalStateException("CSV not found at: " + citiesCsvFile.toAbsolutePath());
        }

        try (Stream<City> cities = CsvReader.streamCsv(citiesCsvFile, City.class)) {
            return cities
                    .filter(c -> c.getPopulation() < 100 && c.getPopulation() > 10)
                    .collect(Collectors.toList());
        }
    }

}