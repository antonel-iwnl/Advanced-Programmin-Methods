package domain;
import java.util.Date;

public class Activity {
    private String date;
    private Integer nr_steps;
    private Double hrs_sleep;
    private String physActivities;
    private Integer move_minutes;

    public Activity(String date, Integer steps, Double sleep, String physActivities, Integer move_minutes) {
        this.date = date;
        this.nr_steps = steps;
        this.hrs_sleep = sleep;
        this.physActivities = physActivities;
        this.move_minutes = move_minutes;
    }

    public String getDate() {
        return date;
    }

    public Integer getNr_steps() {
        return nr_steps;
    }

    public Double getHrs_sleep() {
        return hrs_sleep;
    }

    public String getPhysActivities() {
        return physActivities;
    }

    public Integer getMove_minutes() {
        return move_minutes;
    }

    @Override
    public String toString() {
        return date + "; " + nr_steps + "; " + hrs_sleep + "; " + physActivities + "; " + move_minutes;
    }
}
