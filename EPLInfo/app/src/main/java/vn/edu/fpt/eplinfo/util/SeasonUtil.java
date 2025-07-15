package vn.edu.fpt.eplinfo.util;

import java.util.Calendar;

public class SeasonUtil {

    public static String getCurrentSeason() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        if (month < 7) {
            year--;
        }
        return String.valueOf(year);
    }

    public static String getPreviousSeason() {
        return String.valueOf(Integer.parseInt(getCurrentSeason()) - 1);
    }

    public static String getSeasonDisplay(String season) {
        int year = Integer.parseInt(season);
        return season + "-" + (year + 1);
    }
}