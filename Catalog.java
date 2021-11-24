import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Catalog extends  Thread {
    private List<Album> albumList;
    private List<Ad> adsList;
    public Playlist playlist;
    public int songCounter = 0;

    public Catalog() {
        this.albumList = new ArrayList<Album>();
        this.adsList = new ArrayList<Ad>();
        this.playlist = new Playlist();
    }

    public void addAlbum(Album a) {
        albumList.add(a);
    }

    public void addAd(Ad a) {
        adsList.add(a);
    }

    public Track findSong(String title) {
        for(Album x : albumList) {
            Track z = x.findSong(title);
            if(z != null) return z;
        }

        return null;
    }

    public Ad getRandomAd() {
        Random random = new Random();
        int index = random.nextInt(adsList.size());
        return adsList.get(index);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "albumList=" + albumList +
                ", adsList=" + adsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(albumList, catalog.albumList) && Objects.equals(adsList, catalog.adsList);
    }

    public void run() {
        synchronized (playlist.tracks) {
            for(int i=0; i<10; i++) {
                Random random = new Random();
                int randSong = random.nextInt(this.songCounter);
                int randAd   = random.nextInt(this.adsList.size());
                int song=0,ad=0;
                Song songToAdd = null; Ad adToAdd = null;


                for(Ad x : this.adsList) {
                    if(ad == randAd) {
                        adToAdd = x;
                        break;
                    }
                    ad++;
                }
                for(Album x : this.albumList) {
                    for(Track y : x.getTrackList()) {
                        if(song == randSong) songToAdd = (Song)y;

                        song++;
                    }
                }

                playlist.addTrack(songToAdd);
                playlist.addTrack(adToAdd);
            }

            System.out.println(this.playlist.toString());
        }
    }
}
