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

import edu.uncc.assignment05.MainActivity;
import edu.uncc.assignment05.databinding.FragmentSelectGroupBinding;
import edu.uncc.assignment05.models.Data;

public class SelectGroupFragment extends Fragment {

    FragmentSelectGroupBinding binding;

    String[] groupsList;

    ArrayAdapter<String> adapter;

    public SelectGroupFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGroupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        groupsList = Data.groups;

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, groupsList);

        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String group = groupsList[position];
                Log.d(MainActivity.TAG, "Group selected: " + group);
                mListener.sendGroup(group);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelGroup();
            }
        });
    }

    SelectGroupFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectGroupFragmentInterface) context;
    }

    public interface SelectGroupFragmentInterface {
        void sendGroup(String group);
        void cancelGroup();
    }
}