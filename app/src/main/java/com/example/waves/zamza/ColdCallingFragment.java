package com.example.waves.zamza;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ColdCallingFragment extends Fragment {
    private RecyclerView callingRecyclerView;
    private ColdAdapter coldAdapter;
    private FloatingActionButton floatingActionButton ;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cold_calling , container , false);
        callingRecyclerView = (RecyclerView)view.findViewById(R.id.cold_calling_rv);
        callingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.add_contact);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewContactActivity.class);
                startActivity(intent);
            }
        });
        updateUI();
        return view;
    }
    private void updateUI (){
        ColdCallingLab coldCallingLab = ColdCallingLab.get(getActivity());
        List<ColdCalling> coldCallings = coldCallingLab.getmColdCalling();
        coldAdapter = new ColdAdapter(coldCallings);
        callingRecyclerView.setAdapter(coldAdapter);
    }
    private class ColdHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nameTextView;
        private ColdCalling mColdCalling;
        public ColdHolder( View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindCold (ColdCalling coldCalling){
    mColdCalling = coldCalling;
//    nameTextView.setText(mColdCalling.getNameCalling());
        }
    }
    private class ColdAdapter extends RecyclerView.Adapter<ColdHolder>{
        private List<ColdCalling>mColdCalling;
        public ColdAdapter (List <ColdCalling>coldCalling){
            mColdCalling = coldCalling;
        }



        @Override
        public ColdHolder onCreateViewHolder( ViewGroup parent, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_cold_calling , parent, false);
            return new ColdHolder(view);
        }

        @Override
        public void onBindViewHolder( ColdHolder holder, int position) {
        ColdCalling coldCalling =mColdCalling.get(position);
        holder.bindCold(coldCalling);
        }

        @Override
        public int getItemCount() {
            return mColdCalling.size();
        }
    }
}
