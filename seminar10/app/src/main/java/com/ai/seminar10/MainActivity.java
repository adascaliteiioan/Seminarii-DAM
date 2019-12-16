package com.ai.seminar10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbReference = database.getReference("users");
    private Button btnWrite;
    private Button btnRead;
    private Button btnUpdate;
    private Button btnDelete;
    private Random random = new Random();
    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btn_read);
        btnWrite = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = UUID.randomUUID().toString();
                User user = new User(userId, "Nume " + random.nextInt());
                dbReference.child(user.getId()).setValue(user);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, dbReference.child(userId).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(UUID.randomUUID().toString(), "Nume " + random.nextInt());
                dbReference.child(userId).setValue(user);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference.child(userId).removeValue();

            }
        });

        addUserChangedListener();
    }

    private void addUserChangedListener() {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
