package vn.edu.fpt.eplinfo.util;

import java.util.Calendar;

public class SeasonUtil {

    // Lấy mùa giải hiện tại (dựa trên tháng và năm hiện tại)
    public static String getCurrentSeason() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // 0 = Tháng 1, 6 = Tháng 7, v.v.
        // Mùa giải bắt đầu từ tháng 8 (tháng 7 theo index 0-based) của năm trước
        if (month < 7) { // Trước tháng 8, vẫn là mùa giải của năm trước
            year--;
        }
        return String.valueOf(year);
    }

    // Lấy mùa giải trước
    public static String getPreviousSeason() {
        return String.valueOf(Integer.parseInt(getCurrentSeason()) - 1);
    }

    // Lấy chuỗi hiển thị mùa giải (ví dụ: "2024-2025")
    public static String getSeasonDisplay(String season) {
        int year = Integer.parseInt(season);
        return season + "-" + (year + 1);
    }
}