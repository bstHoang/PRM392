package vn.edu.fpt.eplinfo.model;

import java.util.List;

public class StandingResponse {
    private List<Standing> standings;

    public List<Standing> getStandings() {
        return standings;
    }

    public static class Standing {
        private List<TableItem> table;
        public List<TableItem> getTable() {
            return table;
        }
    }

    public static class TableItem {
        private int position;
        private int points;
        private Team team;

        public int getPosition() { return position; }
        public int getPoints() { return points; }
        public Team getTeam() { return team; }
    }

    public static class Team {
        private String name;
        public String getName() { return name; }
    }
}