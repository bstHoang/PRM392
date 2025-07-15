package vn.edu.fpt.eplinfo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.fpt.eplinfo.R;
import vn.edu.fpt.eplinfo.model.StandingResponse;

import java.util.List;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder> {
    private List<StandingResponse.Table> tableList;

    public StandingAdapter(List<StandingResponse.Table> tableList) {
        this.tableList = tableList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPosition, tvTeamName, tvPlayedGames, tvWon, tvDraw, tvLost, tvPoints;

        public ViewHolder(View view) {
            super(view);
            tvPosition = view.findViewById(R.id.tvPosition);
            tvTeamName = view.findViewById(R.id.tvTeamName);
            tvPlayedGames = view.findViewById(R.id.tvPlayedGames);
            tvWon = view.findViewById(R.id.tvWon);
            tvDraw = view.findViewById(R.id.tvDraw);
            tvLost = view.findViewById(R.id.tvLost);
            tvPoints = view.findViewById(R.id.tvPoints);
        }
    }

    @Override
    public StandingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_standing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StandingAdapter.ViewHolder holder, int position) {
        StandingResponse.Table table = tableList.get(position);
        holder.tvPosition.setText(String.valueOf(table.getPosition()));
        holder.tvTeamName.setText(table.getTeam().getName());
        holder.tvPlayedGames.setText(String.valueOf(table.getPlayedGames()));
        holder.tvWon.setText(String.valueOf(table.getWon()));
        holder.tvDraw.setText(String.valueOf(table.getDraw()));
        holder.tvLost.setText(String.valueOf(table.getLost()));
        holder.tvPoints.setText(String.valueOf(table.getPoints()));
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    // Cập nhật dữ liệu khi lọc
    public void updateData(List<StandingResponse.Table> newList) {
        this.tableList = newList;
        notifyDataSetChanged();
    }
}