package com.example.waves.zamza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.*;
import android.widget.*;

import java.util.List;

public class ColdCallingListFragment extends Fragment {
    private RecyclerView mColdCallingRecyclerView;
    private ColdAdapter mAdapter;
    private Callbacks mCallbacks;


    private FloatingActionButton addContact;

    public interface Callbacks{
        void onCallSelected(ColdCalling coldCalling);
    }
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;

    }
@Override
public void onDetach (){
        super.onDetach();
        mCallbacks = null;
}

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cold_calling, container, false);
        addContact = (FloatingActionButton)view.findViewById(R.id.add_contact);
        mColdCallingRecyclerView = (RecyclerView) view.findViewById(R.id.cold_calling_rv);

        mColdCallingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColdCalling coldCalling = new ColdCalling();
                ColdCallingLab.get(getActivity()).addContact(coldCalling);
               mCallbacks.onCallSelected(coldCalling);
            }
        });

//drag & drop swipe delete number
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public boolean isLongPressDragEnabled(){
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    mAdapter.deleteContact(viewHolder.getAdapterPosition());
                    updateUI();
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(mColdCallingRecyclerView);
updateUI();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        ColdCallingLab coldCallingLab = ColdCallingLab.get(getActivity());
        List<ColdCalling> coldCallings = coldCallingLab.getColdCalling();

        if (mAdapter == null) {
            mAdapter = new ColdAdapter(coldCallings);
            mColdCallingRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNumber(coldCallings);
            mAdapter.notifyDataSetChanged();
        }

    }
    private class CallHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ColdCalling mColdCalling;
        private TextView mNameNumber;
        private TextView mNumber;
        private CheckBox mCallComplete;
        private Button callContacts;


        public CallHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            callContacts = (Button)itemView.findViewById(R.id.call_contact);
            callContacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + mColdCalling.getNumberCalling()));
                    startActivity(intent);
                }
            });

            mNameNumber = (TextView)
                    itemView.findViewById(R.id.name);
            mNumber = (TextView)
                    itemView.findViewById(R.id.number);
            mCallComplete = (CheckBox)
                    itemView.findViewById(R.id.call_complete_checkbox);

        }

        public void bindCall(ColdCalling coldCalling) {
            mColdCalling = coldCalling;
            mNameNumber.setText(mColdCalling.getNameCalling());
            mNumber.setText(mColdCalling.getNumberCalling());
            mCallComplete.setChecked(mColdCalling.isCallComplete());

        }

        @Override
        public void onClick(View view) {
            mCallbacks.onCallSelected(mColdCalling);
        }
    }

    private class ColdAdapter extends RecyclerView.Adapter<CallHolder> {

        private List<ColdCalling> mColdCalling;

        @Override
        public void onBindViewHolder(CallHolder holder, int position) {
            ColdCalling coldCalling = mColdCalling.get(position);
            holder.bindCall(coldCalling);
            holder.bindCall(coldCalling);
        }

        public ColdAdapter(List<ColdCalling> coldCallings) {
            mColdCalling = coldCallings;
        }

        @Override
        public CallHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.list_item_cold_calling, parent, false);
            return new CallHolder(view);
        }



        @Override
        public int getItemCount() {
            return mColdCalling.size();
        }

        public void setNumber(List<ColdCalling> coldCallings) {
            mColdCalling = coldCallings;
        }

        public void deleteContact(int position) {
            ColdCallingLab coldCallingLab = ColdCallingLab.get(getActivity());
            ColdCalling coldCalling = mColdCalling.get(position);
            coldCallingLab.deleteContact(coldCalling);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyItemRangeChanged(position, coldCallingLab.getColdCalling().size());
            Toast.makeText(getContext(), R.string.delete_number, Toast.LENGTH_SHORT).show();
        }
    }

}