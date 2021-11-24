import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Provide the filename:");
        Scanner keyboardInput = new Scanner(System.in);
        String filename = keyboardInput.next();
        Catalog catalog = new Catalog();
        catalog.playlist = new Playlist();
        Main.readFromFile(filename,catalog);

        boolean continueProgram = true;

        while(continueProgram) {
            System.out.println("1 - Print all albums & songs\n" +
                               "2 - Add a new advertisement\n" +
                               "3 - Enable shuffling of songs\n" +
                               "4 - Add song to front of playlist\n" +
                               "5 - Write to file\n" +
                               "6 - Calculate commercial value\n" +
                               "7 - Stop the program\n");

            int choice = keyboardInput.nextInt();
            switch(choice) {
                case 1: //Print all
                    System.out.println(catalog.toString());
                    break;

                case 2: //New advert
                    System.out.println("Provide the advert company:");
                    String company = keyboardInput.next();
                    System.out.println("Provide the duration of the advert in format MM:SS");
                    Duration adDuration = Duration.stringToDuration(keyboardInput.next());
                    System.out.println("Provide the price in euros:");
                    double price = keyboardInput.nextDouble();
                    catalog.addAd(new Ad(company,adDuration,price));
                    break;

                case 3:
                    catalog.playlist = new Playlist();
                    catalog.start();
                    break;

                case 4:
                    System.out.println("Provide the title of the song");
                    String song = keyboardInput.next();
                    Track outputSong = catalog.findSong(song);
                    if(outputSong == null) System.out.println("There's no such song in the catalog");
                    else {
                        catalog.playlist.addTrack((Song)outputSong);
                        catalog.playlist.addTrack((Ad)catalog.getRandomAd());
                        System.out.println(catalog.playlist.toString());
                    }

                    break;

                case 5:
                    System.out.println("Provide the filename");
                    String outputFile = keyboardInput.next();
                    FileWriter writer = new FileWriter(outputFile);
                    writer.write(catalog.toString());
                    writer.close();
                    break;

                case 6:
                    double score=0.0;
                    if(catalog.playlist != null) System.out.println(catalog.playlist.getValue());
                    else System.out.println(0.0);
                    break;

                case 7:
                    continueProgram = false;
                    break;
            }
        }
    }

    private static void readFromFile(String filename, Catalog catalog) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(filename));
        String artist="", title="";
        int releaseDate = -1;
        List<String> artistList = new ArrayList<String>();
        List<Track> songList    = new ArrayList<Track>();


        while(fileRead.hasNextLine()) {
            String line = fileRead.nextLine();

            if(line.contains("ALBUM ")) {
                if(!artist.equals("")) {
                    catalog.addAlbum(new Album(artist,title,releaseDate,songList,artistList));
                }

                String[] elems = line.split("; ");
                artist = elems[0].replace("ALBUM ","");
                title = elems[1];
                releaseDate = Integer.parseInt(elems[2]);
            } else if(line.contains("ARTISTS ")) {
                artistList = new ArrayList<String>();
                String[] elems = line.split("; ");
                elems[0] = elems[0].replace("ARTISTS ","");
                for(int i=0; i<elems.length; i++) artistList.add(elems[i]);

                songList = new ArrayList<Track>();
            } else if(line.contains("TRACK ")) {
                catalog.songCounter++;
                String[] elems = line.split("; ");
                songList.add(new Song(elems[1],Duration.stringToDuration(elems[2])));
            } else if(line.contains("ADS") && !artist.equals("")) {
                catalog.addAlbum(new Album(artist,title,releaseDate,songList,artistList));
            } else if(line.contains("AD ")) {
                String[] elems = line.split("; ");
                catalog.addAd(new Ad(elems[0].replace("AD ",""),Duration.stringToDuration(elems[1]),Double.parseDouble(elems[2].replace(" euros",""))));
            }
        }
    }
}
