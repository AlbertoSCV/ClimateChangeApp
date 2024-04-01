package com.example.climatechangeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    private boolean anonymous;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        anonymous = intent.getBooleanExtra("anonymous", false);

        Button comments;
        comments = findViewById(R.id.commentsBtn);
        comments.setOnClickListener(view -> goToComments());
    }

    private void goToComments(){
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("anonymous", anonymous);
        startActivity(intent);
    }
}