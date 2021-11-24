import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Playlist {
    public List<Track> tracks;
    private int adCounter,songCounter;
    public Playlist() {
        this.tracks = new ArrayList<>();
        this.adCounter   = 0;
        this.songCounter = 0;
    }

    public void addTrack(Track b) {
        if(b instanceof Ad) adCounter++;
        else songCounter++;

        tracks.add(b);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(tracks, playlist.tracks);
    }

    public double getValue() {
        double acummulated = (tracks.size() == 0 ? 0 : tracks
                .stream()
                .filter(x -> x instanceof Ad)
                .map(x -> ((Ad) x).getPrice()*x.getDuration().getTime())
                .collect(Collectors.summingDouble(Double::doubleValue)));
        return acummulated;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "tracks=" + tracks +
                '}';
    }

}
