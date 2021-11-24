import java.util.Objects;

public class Duration {
    private int minutes;
    private int seconds;


    public Duration(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public static Duration stringToDuration(String data) {
        String[] separated = data.split(":");
        return new Duration(Integer.parseInt(separated[0]),Integer.parseInt(separated[1]));
    }

    public double getTime() {
        return (double)minutes + ((double)seconds)/60;
    }

    @Override
    public String toString() {
        return minutes+":"+seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return minutes == duration.minutes && seconds == duration.seconds;
    }



}
