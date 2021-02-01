package com.willyweathermachinetest.view.playerList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.willyweathermachinetest.R;
import com.willyweathermachinetest.view.playerDetail.PlayerDetailActivity;
import com.willyweathermachinetest.model.playerList.Player;
import com.willyweathermachinetest.sharedpreferences.SharedPreferencesName;

public class PlayerListAdapter extends PagedListAdapter<Player,PlayerListAdapter.PlayerViewHolder>{
    private Context mContext;
    public PlayerListAdapter(Context mContext) {
        super(USER_COMPARATOR);
        this.mContext = mContext;
    }
    protected PlayerListAdapter(@NonNull DiffUtil.ItemCallback<Player> diffCallback) {
        super(diffCallback);
    }
    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subitem_countrylist, parent, false);
        PlayerListAdapter.PlayerViewHolder holder = new PlayerListAdapter.PlayerViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(getItem(position),position);
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        TextView labelName, labelPosition, labelHeight,labelWeight;
        LinearLayout layoutRoot;
        public PlayerViewHolder(View itemView) {
            super(itemView);
            labelName = itemView.findViewById(R.id.lblName);
            labelPosition = itemView.findViewById(R.id.lblPosition);
            labelHeight = itemView.findViewById(R.id.lblHeight);
            labelWeight = itemView.findViewById(R.id.lblWeight);
            layoutRoot = itemView.findViewById(R.id.layoutRoot);
            layoutRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Player selectedPlayer = getItem(position);
                        Intent intent = new Intent(mContext, PlayerDetailActivity.class);
                        intent.putExtra(SharedPreferencesName.PLAYER_DETAIL, new Gson().toJson(selectedPlayer,Player.class));
                        mContext.startActivity(intent);
                    }
                }
            });
        }
        void bind(Player player,int position) {
            if (player.getFirstName() != null && player.getLastName() != null)
                labelName.setText(String.format("%s %s", player.getFirstName(), player.getLastName()));
            if (player.getPosition() != null)
                labelPosition.setText(player.getPosition());
            if (player.getHeightFeet() != null && player.getHeightInches()!=null)
                labelPosition.setText(player.getHeightFeet());
            if (player.getWeightPounds() != null)
                labelPosition.setText(player.getWeightPounds());
            if(position%2==0)
                layoutRoot.setBackgroundColor(mContext.getResources().getColor(R.color.color_list));
            else
                layoutRoot.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    private static final DiffUtil.ItemCallback<Player> USER_COMPARATOR = new DiffUtil.ItemCallback<Player>() {
        @Override public boolean areItemsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @SuppressLint("DiffUtilEquals")
        @Override public boolean areContentsTheSame(@NonNull Player oldItem, @NonNull Player newItem) {
            return oldItem == newItem;
        }
    };
}

