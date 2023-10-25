package com.example.assignment_3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignment_3.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private String selectedMood;

    public void setSelectedMood(String selectedMood) {
        this.selectedMood = selectedMood;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Main");

        if (selectedMood == null) {
            binding.textViewMoodRating.setText("");
        } else {
            setMoodImage(Integer.parseInt(selectedMood), binding.imageViewFeelImage);
            binding.textViewMoodRating.setText(selectedMood + " out of 4");
        }

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextName.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a Valid Name", Toast.LENGTH_SHORT).show();
                } else if (selectedMood == null) {
                    Toast.makeText(getActivity(), "Select a Mood Rating", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int age = Integer.parseInt(binding.editTextAge.getText().toString());
                        Profile profile = new Profile(name, age, selectedMood);
                        mListener.sendProfile(profile);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity(), "Enter a Valid Age", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.buttonTellUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToMoodSelection();
            }
        });
    }

    MainListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MainListener) context;
    }

    public interface MainListener {
        void goToMoodSelection();
        void sendProfile(Profile profile);
    }

    public static void setMoodImage(int i, ImageView imageView) {
        if (i == 0){
            imageView.setImageResource(R.drawable.not_well);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.sad);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable.ok);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable.good);
        } else if (i == 4) {
            imageView.setImageResource(R.drawable.very_good);
        }
    }
}