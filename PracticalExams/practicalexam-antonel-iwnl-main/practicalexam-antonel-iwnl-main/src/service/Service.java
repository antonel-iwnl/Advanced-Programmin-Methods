package service;

import domain.Activity;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    Activity activity1 = new Activity("2023.01.28", 3256, 7.5, "morning_walk", 12);
    Activity activity2 = new Activity("2023.02.04", 3660, 8.0, "afternoon_walk", 38);
    Activity activity3 = new Activity("2023.02.13", 364, 6.0, "", 0);
    Activity activity4 = new Activity("2023.02.10", 259, 7.5, "swimming", 30);
    Activity activity5 = new Activity("2023.01.18", 2580, 8.5, "morning_walk", 25);

    public void addActivities() {
        repo.add(activity1);
        repo.add(activity2);
        repo.add(activity3);
        repo.add(activity4);
        repo.add(activity5);
    }

    public List<Activity> getAllActivitiesSorted() {
        return repo.getAll().stream().sorted(Comparator.comparing(Activity::getDate)).toList();
    }

    public List<Activity> getActivitiesFilterValue(String minutes) {
        return repo.getAll().stream().filter(activity -> activity.getMove_minutes() > Integer.parseInt(minutes)).toList();
    }
}
