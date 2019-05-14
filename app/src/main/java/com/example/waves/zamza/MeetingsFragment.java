package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;

public class MeetingsFragment extends Fragment {
    private RecyclerView mRecyclerViewMeeting;
    private MeetingAdapter mMeetingAdapter;
    private FloatingActionButton addNewMeetingFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu , menuInflater);
        menuInflater.inflate(R.menu.menu_main_tool_bar , menu);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.onAttach(context);

    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetings, container, false);
        mRecyclerViewMeeting = (RecyclerView)view.findViewById(R.id.meeting_rv);
        mRecyclerViewMeeting.setLayoutManager(new LinearLayoutManager(getActivity()));
        addNewMeetingFAB = (FloatingActionButton)view.findViewById(R.id.add_meeting);
        addNewMeetingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity() , AddMeetingActivity.class);
                startActivity(intent);
            }
        });

        updateUI();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    public void updateUI() {

    }
    private class MeetingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public MeetingHolder(@NonNull View itemView) {
            super(itemView);

        }


        @Override
        public void onClick(View v) {

        }
    }
    private class MeetingAdapter extends RecyclerView.Adapter<MeetingHolder>{

        @NonNull
        @Override
        public MeetingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull MeetingHolder meetingHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
