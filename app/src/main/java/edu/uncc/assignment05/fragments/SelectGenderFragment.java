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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uncc.assignment05.MainActivity;
import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentSelectGenderBinding;
import edu.uncc.assignment05.models.Data;

public class SelectGenderFragment extends Fragment {

    // Set up binding
    FragmentSelectGenderBinding binding;
    // Set up adapter
    ArrayAdapter<String> adapter;
    // Set up Gender Array List
    String[] genders;


    public SelectGenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGenderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        genders = Data.genders;

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, genders);

        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gender = genders[position];
                Log.d(MainActivity.TAG, "Gender selected: " + gender);
                mListener.sendGender(gender);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelGender();
            }
        });

    }

    SelectGenderFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectGenderFragmentInterface) context;
    }

    public interface SelectGenderFragmentInterface {
        void sendGender(String gender);
        void cancelGender();
    }
}