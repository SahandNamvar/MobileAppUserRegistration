package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.nfc.Tag;
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
import edu.uncc.assignment05.databinding.FragmentSelectSortBinding;

public class SelectSortFragment extends Fragment {

    FragmentSelectSortBinding binding;

    int orderType = -1;

    public SelectSortFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectSortBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Log.d(MainActivity.TAG, "onViewCreated Sort: orderType = " + orderType);

        binding.imageViewNameAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Name ASC");
                mListener.sendSortSelection((-1) * orderType, "Name");
            }
        });

        binding.imageViewNameDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Name DESC");
                mListener.sendSortSelection(orderType, "Name");
            }
        });

        binding.imageViewEmailAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Email ASC");
                mListener.sendSortSelection((-1) * orderType, "Email");
            }
        });

        binding.imageViewEmailDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Email DESC");
                mListener.sendSortSelection(orderType, "Email");
            }
        });

        binding.imageViewGenderAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Gender ASC");
                mListener.sendSortSelection((-1) * orderType, "Gender");
            }
        });

        binding.imageViewGenderDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Gender DESC");
                mListener.sendSortSelection(orderType, "Gender");
            }
        });

        binding.imageViewAgeAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Age ASC");
                mListener.sendSortSelection((-1) * orderType, "Age");
            }
        });

        binding.imageViewAgeDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Age DESC");
                mListener.sendSortSelection(orderType, "Age");
            }
        });

        binding.imageViewStateAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: State ASC");
                mListener.sendSortSelection((-1) * orderType, "State");
            }
        });

        binding.imageViewStateDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: State DESC");
                mListener.sendSortSelection(orderType, "State");
            }
        });

        binding.imageViewGroupAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Group ASC");
                mListener.sendSortSelection((-1) * orderType, "Group");
            }
        });

        binding.imageViewGroupDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "Sort: Group DESC");
                mListener.sendSortSelection(orderType, "Group");
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelSort();
            }
        });
    }

    // Interface to send the selection sort method back to Main Activity to be send to UsersFragment
    SelectSortFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectSortFragmentInterface) context;
    }

    public interface SelectSortFragmentInterface {
        void sendSortSelection(int orderType, String fieldToSort);
        void cancelSort();
    }
}