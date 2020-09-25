package com.example.coachy.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coachy.R;
import com.example.coachy.models.Coach;
import com.example.coachy.models.TrainingType;
import com.example.coachy.models.UploadFirebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsTrainingAdapter extends RecyclerView.Adapter<DetailsTrainingAdapter.ViewHolder>  {

    private List<Coach> mDataSet;
    private Context context;
//    private TrainingTypeAdapter.OnClickSelected callback;

    public DetailsTrainingAdapter(Context context, List<Coach> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
 //       this.callback =(TrainingTypeAdapter.OnClickSelected)context;

    }

    public interface OnClickSelected{

        public void onTrainingTypeSelected(TrainingType trainingType);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_training_row,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     //   holder.trainingType.setImageResource(mDataSet.get(position).getTrainingImage());

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.playVideoBtn.setBackgroundTintList(ColorStateList.valueOf(color));

        holder.cardView.setOnClickListener(b->{
           // callback.onTrainingTypeSelected(mDataSet.get(position));
        });

        Picasso.get().load(mDataSet.get(position).getProfileImage()).placeholder(R.drawable.aerobic).into(holder.coachProfile);
        holder.coachName.setText(mDataSet.get(position).getFullName());

    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
  //      callback = null;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView coachProfile;
        private CardView cardView;
        private FloatingActionButton playVideoBtn;
        private TextView coachName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coachProfile = itemView.findViewById(R.id.im_coach_profile);
            cardView = itemView.findViewById(R.id.parent_layout);
            playVideoBtn = itemView.findViewById(R.id.fab_play);
            coachName = itemView.findViewById(R.id.tv_coach_name);
        }
    }


}

