package com.example.souhardkataria.ruralt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TabTwo extends Fragment {
    DatabaseReference mRef;
    ListView questionList;
    ArrayList<Question> questions=new ArrayList<>();
    ArrayList<Boolean> flag=new ArrayList<>();
    ArrayList<String> ids=new ArrayList<>();
    public TabTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_two,container,false);
        questionList=view.findViewById(R.id.forumquestionlist);
        final Adapter adapter=new Adapter();
        questionList.setAdapter(adapter);
        mRef= FirebaseDatabase.getInstance().getReference("Forum");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questions.clear();
                flag.clear();
                ids.clear();
                for(DataSnapshot x:dataSnapshot.getChildren())
                {
                    Question a=x.getValue(Question.class);
                    questions.add(a);
                    if(a.answer==null||a.answer.equals(""))
                    {
                        flag.add(false);
                    }else {
                        flag.add(true);
                    }
                    ids.add(x.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }



    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Context context=getContext();
            view=((FragmentActivity)context).getLayoutInflater().inflate(R.layout.forum_question_element,viewGroup,false);
            TextView tv=view.findViewById(R.id.question);
            tv.setText(questions.get(i).query);
            final int im=i;
            if(flag.get(i))
            {
                tv.setTextColor(getResources().getColor(R.color.answered));
            }else {
                tv.setTextColor(getResources().getColor(R.color.unanswered));
            }
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(getContext(),ForumAnswer.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    in.putExtra("id",ids.get(im));
                    startActivity(in);
                }
            });
            return view;
        }
    }
}
