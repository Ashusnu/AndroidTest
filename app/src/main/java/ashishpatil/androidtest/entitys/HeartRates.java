package ashishpatil.androidtest.entitys;

/**
 * Created by ashusonu on 23/03/18.
 */

public class HeartRates {

    public int id;
    public String date;
    public String time;
    public String value;

    public HeartRates(int id, String date, String time, String value) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
