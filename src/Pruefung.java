import java.time.LocalDateTime;

public class Pruefung {
    String key;  // Name.--.PNr.--.Version.--.Stg
    LocalDateTime begin;
    LocalDateTime end;
    String zeit;
    String datum;

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getKey() {
        return key;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
