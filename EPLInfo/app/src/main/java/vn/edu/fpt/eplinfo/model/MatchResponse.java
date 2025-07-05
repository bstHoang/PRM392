package vn.edu.fpt.eplinfo.model;

import java.util.List;

public class MatchResponse {
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public static class Match {
        private Team homeTeam;
        private Team awayTeam;
        private Score score;
        private String utcDate;
        private String status;

        public Team getHomeTeam() { return homeTeam; }
        public Team getAwayTeam() { return awayTeam; }
        public Score getScore() { return score; }
        public String getUtcDate() { return utcDate; }
        public String getStatus() { return status; }
    }

    public static class Team {
        private String name;
        public String getName() { return name; }
    }

    public static class Score {
        private FullTime fullTime;
        public FullTime getFullTime() { return fullTime; }
    }

    public static class FullTime {
        private Integer home;
        private Integer away;
        public Integer getHome() { return home; }
        public Integer getAway() { return away; }
    }
}