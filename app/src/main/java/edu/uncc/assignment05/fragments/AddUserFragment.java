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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.uncc.assignment05.MainActivity;
import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentAddUserBinding;
import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.models.User;

public class AddUserFragment extends Fragment {

    FragmentAddUserBinding binding;
    EditText editTextName, editTextEmail;
    TextView textViewGender, textViewAge, textViewState, textViewGroup;

    private String name, email, gender, age, state, group;

    public AddUserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = binding.editTextName;
        editTextEmail = binding.editTextEmail;

        // onClick Gender
        binding.buttonSelectGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectGenderFragment();
            }
        });

        // onClick Age
        binding.buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectAgeFragment();
            }
        });

        // onClick State
        binding.buttonSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectStateFragment();
            }
        });

        // onClick Group
        binding.buttonSelectGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectGroupFragment();
            }
        });

        // onClick Submit
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameEntered = editTextName.getText().toString();
                name = nameEntered; // Set local name variable
                String emailEntered = editTextEmail.getText().toString();
                email = emailEntered; // Set local email variable

                if (nameEntered.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (emailEntered.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Email!", Toast.LENGTH_SHORT).show();
                } else if (gender == null) {
                    Toast.makeText(getActivity(), "Enter Gender!", Toast.LENGTH_SHORT).show();
                } else if (age == null) {
                    Toast.makeText(getActivity(), "Enter Age!", Toast.LENGTH_SHORT).show();
                } else if (state == null) {
                    Toast.makeText(getActivity(), "Enter State!", Toast.LENGTH_SHORT).show();
                } else if (group == null) {
                    Toast.makeText(getActivity(), "Enter Group!", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, email, gender, Integer.parseInt(age), state, group);
                    Log.d(MainActivity.TAG, "User created: " + user);
                    mListener.sendUserCreated(user);
                }
            }
        });


        // --------------------------------- Update Textview ---------------------------------
        textViewGender = binding.textViewGender;
        if (gender != null) {
            textViewGender.setText(gender);
        }

        textViewAge = binding.textViewAge;
        if (age != null) {
            textViewAge.setText(age);
        }

        textViewState = binding.textViewState;
        if (state != null) {
            textViewState.setText(state);
        }

        textViewGroup = binding.textViewGroup;
        if (group != null) {
            textViewGroup.setText(group);
        }
    }


    // Methods to set attributes locally when received from other fragments
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setState(String state) {this.state = state; }

    public void setGroup(String group) {this.group = group; }

    // Interface
    AddUserFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddUserFragmentInterface) context;
    }

    public interface AddUserFragmentInterface {
        void gotoSelectGenderFragment();
        void gotoSelectAgeFragment();
        void gotoSelectStateFragment();
        void gotoSelectGroupFragment();
        void sendUserCreated(User user);
    }
}