package com.example.assignment_3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_3.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM_PROFILE = "ARG_PARAM_PROFILE";
    private Profile mProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance(Profile profile) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PROFILE, profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfile = (Profile)getArguments().getSerializable(ARG_PARAM_PROFILE);
        }
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewName.setText(mProfile.getName());
        binding.textViewAge.setText(mProfile.getAge() + " Years Old");
        binding.textViewMoodRating.setText(mProfile.getMood() + " out of 4");
        getMoodText(Integer.parseInt(mProfile.getMood()));
        MainFragment.setMoodImage(Integer.parseInt(mProfile.getMood()), binding.imageViewFeelImage);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToMain();
            }
        });
    }

    ProfileFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ProfileFragmentListener) context;
    }

    public interface ProfileFragmentListener {
        void goToMain();
    }

    public void getMoodText(int i) {
        if (i == 0){
            binding.textViewMood.setText("Not Well");
        } else if (i == 1) {
            binding.textViewMood.setText("Bad");
        } else if (i == 2) {
            binding.textViewMood.setText("Okay");
        } else if (i == 3) {
            binding.textViewMood.setText("Good");
        } else if (i == 4) {
            binding.textViewMood.setText("Very Good");
        }
    }
}