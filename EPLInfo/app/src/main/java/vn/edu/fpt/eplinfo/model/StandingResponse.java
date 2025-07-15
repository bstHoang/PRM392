package vn.edu.fpt.eplinfo.model;

import java.util.List;

public class StandingResponse {
    private List<Standing> standings;

    public List<Standing> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing> standings) {
        this.standings = standings;
    }

    public static class Standing {
        private List<Table> table;

        public List<Table> getTable() {
            return table;
        }

        public void setTable(List<Table> table) {
            this.table = table;
        }
    }

    public static class Table {
        private int position;
        private Team team;
        private int playedGames;
        private int won;
        private int draw;
        private int lost;
        private int points;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public int getPlayedGames() {
            return playedGames;
        }

        public void setPlayedGames(int playedGames) {
            this.playedGames = playedGames;
        }

        public int getWon() {
            return won;
        }

        public void setWon(int won) {
            this.won = won;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getLost() {
            return lost;
        }

        public void setLost(int lost) {
            this.lost = lost;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

    public static class Team {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}