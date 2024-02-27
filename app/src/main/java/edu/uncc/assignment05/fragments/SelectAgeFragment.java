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
import edu.uncc.assignment05.databinding.FragmentAddUserBinding;
import edu.uncc.assignment05.databinding.FragmentSelectAgeBinding;
import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.models.Data;

public class SelectAgeFragment extends Fragment {

    FragmentSelectAgeBinding binding;

    ArrayList<String> ageList = new ArrayList<>();

    ArrayAdapter<String> adapter;

    String age;

    public SelectAgeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectAgeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Populate the ArrayList with numbers 1-100
        for (int i = 18; i <= 100; i++) {
            ageList.add(String.valueOf(i));
        }

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, ageList);

        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                age = ageList.get(position);
                Log.d(MainActivity.TAG, "Age selected: " + age);
                mListener.sendAge(age);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelAge();
            }
        });
    }

    SelectAgeFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectAgeFragmentInterface) context;
    }

    public interface SelectAgeFragmentInterface {
        void sendAge(String age);
        void cancelAge();
    }
}