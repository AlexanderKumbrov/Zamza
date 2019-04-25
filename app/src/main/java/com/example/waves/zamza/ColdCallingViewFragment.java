package com.example.waves.zamza;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

public class ColdCallingViewFragment extends Fragment {
    private static final String ARG_CALL_ID = "calling_Id";
    private ColdCalling mColdCalling;


    public static ColdCallingViewFragment newInstance (UUID callId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CALL_ID , callId);

        ColdCallingViewFragment fragment = new ColdCallingViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID callId = (UUID)getArguments().getSerializable(ARG_CALL_ID);
        mColdCalling = ColdCallingLab.get(getActivity()).getColdCalling(callId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cold_calling_view, container, false);

return view;
    }
}
