package vn.edu.fpt.premierleagueinformation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.fpt.premierleagueinformation.R;
import vn.edu.fpt.premierleagueinformation.model.StandingResponse;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder> {
    private List<StandingResponse.TableItem> table;

    public StandingAdapter(List<StandingResponse.TableItem> table) {
        this.table = table;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName, points, position;

        public ViewHolder(View view) {
            super(view);
            teamName = view.findViewById(R.id.tvTeamName);
            points = view.findViewById(R.id.tvPoints);
            position = view.findViewById(R.id.tvPosition);
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
        StandingResponse.TableItem item = table.get(position);
        holder.teamName.setText(item.getTeam().getName());
        holder.points.setText("Points: " + item.getPoints());
        holder.position.setText("Position: " + item.getPosition());
    }

    @Override
    public int getItemCount() {
        return table.size();
    }
}
