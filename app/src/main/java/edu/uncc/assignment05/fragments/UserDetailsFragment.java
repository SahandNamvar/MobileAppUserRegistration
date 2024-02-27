package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment05.MainActivity;
import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentUserDetailsBinding;
import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.models.User;

public class UserDetailsFragment extends Fragment {

    FragmentUserDetailsBinding binding;

    private static final String ARG_PARAM_USER_KEY = "ARG_PARAM_USER_KEY"; // For receiving a User object

    private User mUserObjectReceived; // For storing the User object received

    public UserDetailsFragment() {}

    public static UserDetailsFragment newInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER_KEY, user); // Receive Serializable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserObjectReceived = (User) getArguments().getSerializable(ARG_PARAM_USER_KEY); // Set local User = Serializable User received
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set textViews based on User attributes
        if (mUserObjectReceived != null) {
            binding.textViewName.setText(mUserObjectReceived.getName());
            binding.textViewEmail.setText(mUserObjectReceived.getEmail());
            binding.textViewAge.setText(String.valueOf(mUserObjectReceived.getAge()));
            binding.textViewState.setText(mUserObjectReceived.getState());
            binding.textViewGender.setText(mUserObjectReceived.getGender());
            binding.textViewGroup.setText(mUserObjectReceived.getGroup());
        }

        // onClick Back Button
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goBack();
            }
        });

        // onClick Delete imageView
        binding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteUser(mUserObjectReceived);
                Log.d(MainActivity.TAG, "onClick: Delete User: " + mUserObjectReceived);
            }
        });


    }

    // Interface for sending the User back to Main Activity to be deleted from the Array List
    UserDetailsFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UserDetailsFragmentInterface) context;
    }

    public interface UserDetailsFragmentInterface {
        void goBack();
        void deleteUser(User user);
    }
}