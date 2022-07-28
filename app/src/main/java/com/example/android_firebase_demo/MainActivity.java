package com.example.android_firebase_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>(){

            @Override
            public void onComplete(@NonNull Task<String> task) {

                if(!task.isSuccessful()){
                    Log.e("TokenDetails", "Token Failed in Recieved !");
                    return;
                }

                String token = task.getResult();
                Log.d("Token", token);
            }
        });


        // single node data
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Copyright");

        databaseReference.setValue("WsCubeTech All Rights Are Reserved.");



        // multiple nested child data
        DatabaseReference contactRef = FirebaseDatabase.getInstance().getReference("Contacts");

        String contactID = contactRef.push().getKey();

        ContactModel contactModel = new ContactModel("Ram", "8045234567");

        contactRef.child(contactID).setValue(contactModel);



        // Receiving data
        contactRef.child(contactID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ContactModel model = snapshot.getValue(ContactModel.class);

                Log.d("Contact", model.getName() + "," + model.getMobileNo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("DBError", error.toString());
            }
        });

    }
}