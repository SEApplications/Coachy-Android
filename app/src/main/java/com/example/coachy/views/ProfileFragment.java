package com.example.coachy.views;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coachy.R;
import com.example.coachy.adapters.VideosAdapter;
import com.example.coachy.models.Coach;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements DialogInterface.OnDismissListener {

    private Button createProfile;
    private TextView coachName, coachAge, coachSeniority, coachDescription, coachSpecialize;
    private ImageView diploma;
    private CircleImageView profuleImage;
    private RecyclerView videoRecyclerView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeViews(v);


        //Read from the database
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("coaches").child("0");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Coach coach = dataSnapshot.getValue(Coach.class);
                coachName.setText(coach.getFullName());
                coachAge.setText(String.valueOf(coach.getAge()));
                coachSeniority.setText(coach.getSeniority());
                coachDescription.setText(coach.getDescription());

                for (int i = 0; i < coach.getSpecialize().size(); i++) {
                    coachSpecialize.append(coach.getSpecialize().get(i) + ", ");
                }


                Picasso.get().load(coach.getDiploma()).into(diploma);
                Picasso.get().load(coach.getProfileImage()).into(profuleImage);


                VideosAdapter adapter = new VideosAdapter(getContext(),coach.getVideo());
                videoRecyclerView.setHasFixedSize(true);
                videoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                videoRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return v;
    }

    private void initializeViews(View view){
        coachName = view.findViewById(R.id.coach_name);
        coachAge = view.findViewById(R.id.coach_age);
        coachDescription = view.findViewById(R.id.coach_description);
        coachSeniority = view.findViewById(R.id.coach_seniority);
        coachSpecialize = view.findViewById(R.id.coach_specialize);
        diploma = view.findViewById(R.id.diploma_image_view);
        profuleImage = view.findViewById(R.id.im_coach_profile);
        videoRecyclerView = view.findViewById(R.id.videos_rv);

        createProfile = view.findViewById(R.id.create_profile_button);
        createProfile.setVisibility(View.VISIBLE);

        createProfile.setOnClickListener(b->{
            Dialog_CochApproach dialog = new Dialog_CochApproach();
            dialog.show(getChildFragmentManager(), dialog.getTag());
        });
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }
}