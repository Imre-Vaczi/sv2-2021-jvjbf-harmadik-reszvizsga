package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class SongService {

    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song can not be null.");
        }
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs.stream()
                .min(Comparator.comparing(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream()
                .filter(s -> s.getTitle().equals(title))
                .toList();
    }

    public Boolean isPerformerInSong(Song s, String performer) {
        return s.getPerformers().stream()
                .anyMatch(o -> o.equals(performer));
    }

    public List<String> titlesBeforeDate(LocalDate date) {
        return songs.stream()
                .filter(s -> s.getRelease().isBefore(date))
                .map(i -> i.getTitle())
                .toList();
    }
}
