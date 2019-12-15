package com.example.ahabdelhak.otocabtask.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahabdelhak.otocabtask.Model.Users;
import com.example.ahabdelhak.otocabtask.R;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class RegisteredUseres extends AppCompatActivity {

    ListView Lv ;
    List<Users> universityList;
    List<String> EmailUsers = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listusers);

        Lv=findViewById(R.id.Lv);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =    mFirebaseDatabase.getReference("NewUsers");

        universityList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                universityList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Users user = postSnapshot.getValue(Users.class);
                    universityList.add(user);

                    // here you can access to name property like university.name
                    for (int i=0;i<universityList.size();i++)
                    {
                        Log.e("Email",universityList.get(i).getEmail());

                        //tv.setText(universityList.get(i).getEmail());
                        EmailUsers.add(universityList.get(i).getEmail());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisteredUseres.this,
                                android.R.layout.simple_list_item_1, android.R.id.text1, EmailUsers);
                    Lv.setAdapter(adapter);
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    void AddUsersMarkers(){
        DatabaseReference f_database = FirebaseDatabase.getInstance().getReference("NewUsers");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                        Users users = snapshot.getValue(Users.class);

                        String email = users.email;
                        System.out.println("emails " + email);

//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                                android.R.layout.simple_list_item_1, android.R.id.text1, values);

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        f_database.addListenerForSingleValueEvent(listener);
    }

}
