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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.uncc.assignment05.MainActivity;
import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.models.User;

public class UsersFragment extends Fragment {

    FragmentUsersBinding binding;

    private ArrayList<User> mUsers = new ArrayList<>();

    UsersAdapter adapter;

    int _orderType = -1;

    public UsersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Call deliverUserList() from Interface to deliver send user ArrayList through setUserData method defined here down below
        mListener.deliverUserList();

        // Set custom adapter for the ListView
        adapter = new UsersAdapter(getActivity(), mUsers);
        binding.listView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // May not be necessary - But after sorting, onViewCreated is called again and it may be better to notify it of any sorting changes

        // onClick AddUser - Go to Add New User Fragment
        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddUser();
            }
        });

        // onClick Clear - Remove all the users from the users ArrayList - Reload the ListView to display no users as the users ArrayList is empty.
        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearList();
                adapter.notifyDataSetChanged(); // Update adapter/listView
            }
        });

        // onItemClick listView
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoUserDetails(position); // Based on the position of the item clicked on, send the position back to Main, get user object from ArrayList via position provided, send that object to DetailsFragment
            }
        });

        // onClick Sort
        binding.buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSort();
            }
        });
    }

    // Receive User Array List from Main Activity upon request - set inside deliverUserList() in Main Activity
    public void setUserData(ArrayList<User> mUsersReceived) {
        mUsers= mUsersReceived;
    }

    // Receive Sort data (sort type: ASC (+1)/DESC (-1)) and the field to sort
    public void sentSortData(int orderType, String fieldToSort) {

        Collections.sort(mUsers, new Comparator<User>() { // Sort data based on fieldToSort value
            @Override
            public int compare(User user1, User user2) {
                if (fieldToSort.equals("Name")) {
                    _orderType = (orderType * user1.getName().compareTo(user2.getName()));
                } else if (fieldToSort.equals("Email")) {
                    _orderType = (orderType * user1.getEmail().compareTo(user2.getEmail()));
                } else if (fieldToSort.equals("Gender")) {
                    _orderType = (orderType * user1.getGender().compareTo(user2.getGender()));
                } else if (fieldToSort.equals("Age")) {
                    _orderType = (orderType * String.valueOf(user1.getAge()).compareTo(String.valueOf(user2.getAge())));
                } else if (fieldToSort.equals("State")) {
                    _orderType = (orderType * user1.getState().compareTo(user2.getState()));
                } else if (fieldToSort.equals("Group")) {
                    _orderType = (orderType * user1.getGroup().compareTo(user2.getGroup()));
                }
                return _orderType;
            }
        });
        adapter.notifyDataSetChanged(); // Update the adapter/ListView
    }

    // Interface to communicate with MainActivity
    UserFragmentInterface mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UserFragmentInterface) context;
    }

    public interface UserFragmentInterface {
        void deliverUserList();
        void gotoAddUser();
        void clearList();
        void gotoUserDetails(int position);
        void gotoSort();
    }

    // Set up Adapter - Returns a view for each object in a collection of data objects you provide
    class UsersAdapter extends ArrayAdapter<User> {
        public UsersAdapter(@NonNull Context context, @NonNull List<User> objects) {
            super(context, R.layout.user_list_item, objects);
        }

        // Implement getView
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.user_list_item, parent, false);
            }

            // TODO: Set up textview via convertView
            TextView textViewName = convertView.findViewById(R.id.textViewName);
            TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
            TextView textViewAge = convertView.findViewById(R.id.textViewAge);
            TextView textViewState = convertView.findViewById(R.id.textViewState);
            TextView textViewGender = convertView.findViewById(R.id.textViewGender);
            TextView textViewGroup = convertView.findViewById(R.id.textViewGroup);

            User user = getItem(position);

            // TODO: Set text to user attributes
            textViewName.setText(user.getName());
            textViewEmail.setText(user.getEmail());
            textViewAge.setText(String.valueOf(user.getAge()));
            textViewState.setText(user.getState());
            textViewGender.setText(user.getGender());
            textViewGroup.setText(user.getGroup());

            return convertView;
        }
    }
}