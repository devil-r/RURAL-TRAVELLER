package com.example.souhardkataria.ruralt;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    //Account
    static String NameUser,DobUser;
    static Bitmap bitmap;
    //  static int Method_Used = 1;

    static ProgressDialog dialog;

    public ChatFragment() {
        // Required empty public constructor
    }

    View view;
    //List
    static ListView chatlist;
    ChatAdapter adapter1;

    String User;
    DatabaseReference reference;

    static ArrayList<String[]> list = new ArrayList<>();

    static int Opened=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_chat, container, false);
        InitializeFields();


        //ListVIew Setup
        adapter1 = new ChatAdapter();
        chatlist.setAdapter(adapter1);


        //User is not null confirmation
        User = "There Goes my Baby";
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            User = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Retrieving data from database
       reference = FirebaseDatabase.getInstance().getReference().child(User).child("chats");

        AtStartUp();
      //  UpdateAccount();
        //   UpdateContacts();


        return view;
    }

    void AtStartUp()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();
                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    String id = data.getValue().toString();
                    String name = data.getKey();

                    list.add(new String[]{id,name});
                    adapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
     }



    private void InitializeFields() {
        chatlist = view.findViewById(R.id.Chat_List);
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Please Wait");
        dialog.setCanceledOnTouchOutside(false);

    }

    class ChatAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent) {

            //Setting up view on the list
            Context context = getContext();
            convertView = ((FragmentActivity) context).getLayoutInflater().inflate(R.layout.chatdesign,parent,false);
            TextView text = convertView.findViewById(R.id.Design_text);
            text.setText(list.get(position)[1]);
            final int i = position;
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ChatFragment.Opened != 0)
                        return;
                    ChatFragment.Opened = 1;
                    Intent intent = new Intent(guidedash.context,ChatActivity.class);
                    intent.putExtra("User",User);
                    intent.putExtra("Reciever",list.get(i)[1]);
                    intent.putExtra("RecieverId",list.get(i)[0]);
                    intent.putExtra("Number",i);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

}



