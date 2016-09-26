package com.example.sam.betterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }


    public void startOver(View view) {
        //view.setY(view.getY() - 20);

        Intent myIntent = new Intent(End.this, Main2Activity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        End.this.startActivity(myIntent);

    }

    public void Main(View view) {
        //view.setY(view.getY() - 20);

        Intent myIntent = new Intent(End.this, MainActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        End.this.startActivity(myIntent);

    }

}
