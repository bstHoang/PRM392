package vn.edu.fpt.eplinfo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import vn.edu.fpt.eplinfo.MatchDetailActivity;
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
        holder.tvStatus.setText(match.getStatus());

        // Chuyển đổi thời gian từ UTC sang giờ Việt Nam (UTC+7)
        String utcDate = match.getUtcDate();
        String formattedDate;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = inputFormat.parse(utcDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            formattedDate = outputFormat.format(date);
            holder.tvDate.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            formattedDate = utcDate;
            holder.tvDate.setText(utcDate);
        }

        MatchResponse.Score score = match.getScore();
        String scoreText;
        if (score != null && score.getFullTime() != null) {
            scoreText = score.getFullTime().getHome() + " - " + score.getFullTime().getAway();
            holder.tvScore.setText(scoreText);
        } else {
            scoreText = "-";
            holder.tvScore.setText("-");
        }

        // Thêm sự kiện nhấn vào mục trận đấu
        String finalFormattedDate = formattedDate;
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MatchDetailActivity.class);
            intent.putExtra("homeTeam", match.getHomeTeam().getName());
            intent.putExtra("awayTeam", match.getAwayTeam().getName());
            intent.putExtra("score", scoreText);
            intent.putExtra("date", finalFormattedDate);
            intent.putExtra("status", match.getStatus());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}