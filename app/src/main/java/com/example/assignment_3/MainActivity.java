package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// Cesar Guzman - Group 34
public class MainActivity extends AppCompatActivity implements MainFragment.MainListener, MoodFragment.MoodListener, ProfileFragment.ProfileFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new MainFragment(), "main-fragment")
                .commit();
    }


    @Override
    public void goToMoodSelection() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new MoodFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancelMoodSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedMood(String selectedMood) {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("main-fragment");
        if (mainFragment != null) {
            mainFragment.setSelectedMood(selectedMood);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void goToMain() {
        getSupportFragmentManager().popBackStack();
    }
}