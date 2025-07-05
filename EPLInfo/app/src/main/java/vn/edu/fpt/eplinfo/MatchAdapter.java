package vn.edu.fpt.eplinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.fpt.eplinfo.R;
import vn.edu.fpt.eplinfo.model.MatchResponse;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<MatchResponse.Match> matches;

    public MatchAdapter(List<MatchResponse.Match> matches) {
        this.matches = matches;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeTeam, tvAwayTeam, tvScore, tvDate, tvStatus;

        public ViewHolder(View view) {
            super(view);
            tvHomeTeam = view.findViewById(R.id.tvHomeTeam);
            tvAwayTeam = view.findViewById(R.id.tvAwayTeam);
            tvScore = view.findViewById(R.id.tvScore);
            tvDate = view.findViewById(R.id.tvDate);
            tvStatus = view.findViewById(R.id.tvStatus);
        }
    }

    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchAdapter.ViewHolder holder, int position) {
        MatchResponse.Match match = matches.get(position);
        holder.tvHomeTeam.setText(match.getHomeTeam().getName());
        holder.tvAwayTeam.setText(match.getAwayTeam().getName());
        holder.tvDate.setText(match.getUtcDate());
        holder.tvStatus.setText(match.getStatus());

        MatchResponse.Score score = match.getScore();
        if (score != null && score.getFullTime() != null) {
            holder.tvScore.setText(score.getFullTime().getHome() + " - " + score.getFullTime().getAway());
        } else {
            holder.tvScore.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}