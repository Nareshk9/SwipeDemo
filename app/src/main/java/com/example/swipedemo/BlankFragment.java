package com.example.swipedemo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swipedemo.Class.MatchDeltails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private RecyclerView mRecycler;
    private MatchStatAdapter mAdapter;
    private LinearLayoutManager mManager;
    private DatabaseReference mFriendReference;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        mRecycler=view.findViewById(R.id.recyclerviewmatchstat);
        mFriendReference= FirebaseDatabase.getInstance().getReference()
                .child("Analytics").child("Cricket").

        return view;
    }

        private static class MatchStatViewHolhder extends RecyclerView.ViewHolder
        {

            TextView team_1,team_2;
            TextView team1_date,team2_date;
            TextView team1_toss,team2_toss;
            TextView team1_score,team2_score;
            TextView team1_wickettaken,team2_wickettaken;
            TextView team1_6s,team2_6s;
            TextView team1_4s,team2_4s;
            TextView team1_extras,team2_extras;
            TextView team1_op,team2_op;
            TextView team1_hscorer,team2_hscorer;
            TextView team1_hwicketTaker,team2_hwicketTaker;
            TextView team1_totalcatches,team2_totalcatches;
            TextView team1_result,team2_result;

            public MatchStatViewHolhder(@NonNull View itemView) {
                super(itemView);
                team_1=itemView.findViewById(R.id.team1);
                team_2=itemView.findViewById(R.id.team2);
                team1_date=itemView.findViewById(R.id.teamA_mdate);
                team2_date=itemView.findViewById(R.id.teamB_mdate);
                team1_toss=itemView.findViewById(R.id.teamA_toss);
                team2_toss=itemView.findViewById(R.id.teamB_toss);
                team1_score=itemView.findViewById(R.id.teamA_score);
                team2_score=itemView.findViewById(R.id.teamB_score);
                team1_wickettaken=itemView.findViewById(R.id.teamA_wicketTaken);
                team2_wickettaken=itemView.findViewById(R.id.teamB_wicketTaken);
                team1_6s=itemView.findViewById(R.id.teamA_total6s);
                team2_6s=itemView.findViewById(R.id.teamB_total6s);
                team1_4s=itemView.findViewById(R.id.teamA_total4s);
                team2_4s=itemView.findViewById(R.id.teamB_total4s);
                team1_extras=itemView.findViewById(R.id.teamA_extras);
                team2_extras=itemView.findViewById(R.id.teamB_extras);
                team1_op=itemView.findViewById(R.id.teamA_op);
                team2_op=itemView.findViewById(R.id.teamB_op);
                team1_hscorer=itemView.findViewById(R.id.teamA_hscorer);
                team2_hscorer=itemView.findViewById(R.id.teamB_hscorer);
                team1_hwicketTaker=itemView.findViewById(R.id.teamA_hwickets);
                team2_hwicketTaker=itemView.findViewById(R.id.teamB_hwickets);
                team1_totalcatches=itemView.findViewById(R.id.teamA_tcatches);
                team2_totalcatches=itemView.findViewById(R.id.teamB_tcatches);
                team1_result=itemView.findViewById(R.id.teamA_result);
                team2_result=itemView.findViewById(R.id.teamB_result);
            }


        }

        private class MatchStatAdapter extends RecyclerView.Adapter<MatchStatViewHolhder>
        {

            private Context mContext;
            private DatabaseReference mDatabaseReference;
            private ChildEventListener mChildEventListener;

            private List<String> MatchIds=new ArrayList<>();
            private List<MatchDeltails> MatchList=new ArrayList<>();

            public MatchStatAdapter(final Context context,DatabaseReference ref)
            {
                mContext=context;
                mDatabaseReference=ref;

                ChildEventListener childEventListener=new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                        MatchDeltails md=dataSnapshot.getValue(MatchDeltails.class);
                        MatchIds.add(dataSnapshot.getKey());
                        MatchList.add(md);
                        notifyItemInserted(MatchList.size()-1);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        MatchDeltails md=dataSnapshot.getValue(MatchDeltails.class);
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {



                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                ref.addChildEventListener(childEventListener);
                mChildEventListener=childEventListener;
            }

            @NonNull
            @Override
            public MatchStatViewHolhder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view=null;
                LayoutInflater inflater=LayoutInflater.from(mContext);
                view=inflater.inflate(R.layout.match_stat_layout,viewGroup,false);
                return new MatchStatViewHolhder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MatchStatViewHolhder matchStatViewHolhder, int i) {


            }

            @Override
            public int getItemCount() {
                return MatchList.size();
            }
        }



}
