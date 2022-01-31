package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

import static java.util.Arrays.asList;

public class MovieTheatreService {

    private Map<String, List<Movie>> movies = new TreeMap<>();

    public void readFromFile(Path path) {

        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String line;
            while ((line = bf.readLine()) != null) {
                String place = line.split("-")[0];
                String title = line.split("-")[1].split(";")[0];
                LocalTime time = LocalTime.parse(line.split("-")[1].split(";")[1]);

                if (!movies.containsKey(place)) {
                    //movies.put(place, Array Arrays.asList(new Movie(title, time)));
                    movies.put(place, new ArrayList(asList(new Movie(title, time))));

                } else {
                    List<Movie> temp = movies.get(place);
                    temp.add(new Movie(title, time));
                    movies.put(place, temp);
                }

            }
        } catch (IOException ioException) {
            throw new IllegalArgumentException("File can not be accessed.");
        }

    }

    public static void main(String[] args) {
        MovieTheatreService mts = new MovieTheatreService();
        Path path = Paths.get("src/test/resources/moviesintheaters.txt");
        mts.readFromFile(path);
        System.out.println(mts.movies);
    }

}
