import java.util.List;

public class Album {
    private String artist;
    private String title;
    private int releaseDate;
    private List<Track> trackList;
    private List<String> artists;

    public Album(String artist, String title, int releaseDate, List<Track> trackList, List<String> artists) {
        this.artist = artist;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trackList = trackList;
        this.artists = artists;
    }

    public void addSong(Song a) {
        this.trackList.add(a);
    }

    public Track findSong(String title) {
        for(Track x : trackList) {
            if(x.getName().equals(title)) return x;
        }

        return null;
    }

    @Override
    public String toString() {
        return artist+"'s Album:" +
                title + '\'' +
                " Released:" + releaseDate +
                "\n trackList=" + trackList +
                ", artists=" + artists;
    }

    public List<Track> getTrackList() {
        return trackList;
    }
}
