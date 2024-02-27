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
import edu.uncc.assignment05.databinding.FragmentSelectStateBinding;
import edu.uncc.assignment05.models.Data;

public class SelectStateFragment extends Fragment {

    FragmentSelectStateBinding binding;

    String[] stateList;

    ArrayAdapter<String> adapter;

    String state;

    public SelectStateFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectStateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stateList = Data.states; // Grab state list from Data Class

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stateList);

        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                state = stateList[position]; // Set state
                Log.d(MainActivity.TAG, "State selected: " + state);
                mListener.sendState(state);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelState();
            }
        });
    }

    // Interface
    SelectStateFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectStateFragmentInterface) context;
    }

    public interface SelectStateFragmentInterface {
        void sendState(String state);
        void cancelState();
    }
}