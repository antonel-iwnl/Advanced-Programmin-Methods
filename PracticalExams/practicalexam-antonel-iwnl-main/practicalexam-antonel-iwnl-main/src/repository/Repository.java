package repository;

import org.sqlite.SQLiteDataSource;
import domain.Activity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void add(Activity activity) {
        try {
            openConnection();
            String insertString = "INSERT INTO fitness VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setString(1, activity.getDate());
                ps.setInt(2, activity.getNr_steps());
                ps.setDouble(3, activity.getHrs_sleep());
                ps.setString(4, activity.getPhysActivities());
                ps.setInt(5, activity.getMove_minutes());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            closeConnection();
        }
    }

    public List<Activity> getAll() {
        List<Activity> activitiesList = new ArrayList<>();
        try {
            openConnection();
            String selectString = "SELECT * FROM fitness;";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String date = rs.getString("date");
                    Integer steps = rs.getInt("number_steps");
                    Double sleep = rs.getDouble("hrs_sleep");
                    String physActivity = rs.getString("activity_name");
                    Integer minutes = rs.getInt("move_minutes");
                    Activity activity = new Activity(date, steps, sleep, physActivity, minutes);
                    activitiesList.add(activity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return activitiesList;
    }
}
