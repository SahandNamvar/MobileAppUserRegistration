package edu.uncc.assignment05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.uncc.assignment05.fragments.AddUserFragment;
import edu.uncc.assignment05.fragments.SelectAgeFragment;
import edu.uncc.assignment05.fragments.SelectGenderFragment;
import edu.uncc.assignment05.fragments.SelectGroupFragment;
import edu.uncc.assignment05.fragments.SelectSortFragment;
import edu.uncc.assignment05.fragments.SelectStateFragment;
import edu.uncc.assignment05.fragments.UserDetailsFragment;
import edu.uncc.assignment05.fragments.UsersFragment;
import edu.uncc.assignment05.models.Data;
import edu.uncc.assignment05.models.User;

public class MainActivity extends AppCompatActivity implements
        UsersFragment.UserFragmentInterface,
        AddUserFragment.AddUserFragmentInterface,
        SelectGenderFragment.SelectGenderFragmentInterface,
        SelectAgeFragment.SelectAgeFragmentInterface,
        SelectStateFragment.SelectStateFragmentInterface,
        SelectGroupFragment.SelectGroupFragmentInterface,
        UserDetailsFragment.UserDetailsFragmentInterface,
        SelectSortFragment.SelectSortFragmentInterface {

    public static final String TAG = "Debug";

    FragmentManager fragmentManager;

    private ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add sample users to Array List for testing...
        mUsers.add(Data.sampleTestUsers.get(0));
        mUsers.add(Data.sampleTestUsers.get(1));
        mUsers.add(Data.sampleTestUsers.get(2));

        fragmentManager = getSupportFragmentManager();

        // Launch AppCategoriesFragment
        fragmentManager.beginTransaction()
                .add(R.id.rootView, new UsersFragment(), "UsersFragment")
                .commit();
    }

    // ---- Override method for UserFragmentInterface

    @Override // Send mUser Array List to UsersFragment upon request
    public void deliverUserList() {
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("UsersFragment");
        if(fragment!=null) {
            fragment.setUserData(mUsers);
        }
    }

    @Override
    public void gotoAddUser() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new AddUserFragment(), "AddUserFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void clearList() {
        mUsers.removeAll(mUsers); // After removing all Users from the ArrayList, notify the adapter of data change
    }

    @Override
    public void gotoUserDetails(int position) {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, UserDetailsFragment.newInstance(mUsers.get(position)), "AddUserFragment") // When clicked on list item, launch UserDetailsFragment and send it the User object via .newInstance
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSort() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectSortFragment(), "SortFragment")
                .addToBackStack(null)
                .commit();
    }

    // ---- Override method for AddUserFragmentInterface
    @Override
    public void gotoSelectGenderFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectGenderFragment(), "GenderFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectAgeFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectAgeFragment(), "AgeFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectStateFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectStateFragment(), "StateFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectGroupFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectGroupFragment(), "GroupFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendUserCreated(User user) {
        mUsers.add(user);
        Log.d(TAG, "User Added in MainActivity: " + mUsers);
        fragmentManager.popBackStack();
    }

    // --- Override method for SelectGenderFragmentInterface
    @Override
    public void sendGender(String gender) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUserFragment");
        if(fragment!=null) {
            fragment.setGender(gender);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelGender() {
        fragmentManager.popBackStack();
    }

    // --- Override method for SelectAgeFragmentInterface
    @Override
    public void sendAge(String age) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUserFragment");
        if(fragment!=null) {
            fragment.setAge(age);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAge() {
        fragmentManager.popBackStack();
    }


    // --- Override method for SelectStateFragmentInterface
    @Override
    public void sendState(String state) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUserFragment");
        if(fragment!=null) {
            fragment.setState(state);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelState() {
        fragmentManager.popBackStack();
    }

    // --- Override method for SelectGroupFragmentInterface
    @Override
    public void sendGroup(String group) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUserFragment");
        if(fragment!=null) {
            fragment.setGroup(group);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelGroup() {
        fragmentManager.popBackStack();
    }

    // --- Override method for UserDetailsFragmentInterface
    @Override
    public void goBack() {
        fragmentManager.popBackStack();
    }

    @Override
    public void deleteUser(User user) {
        mUsers.remove(user);
        fragmentManager.popBackStack();
    }

    // --- Override method for SelectSortFragmentInterface
    @Override
    public void sendSortSelection(int orderType, String fieldToSort) {
        // Log.d(TAG, "sendSortSelection: " + orderType + " " + fieldToSort); // Make sure to receive the correct values.
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("UsersFragment");
        if(fragment!=null) {
            fragment.sentSortData(orderType, fieldToSort);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelSort() {
        fragmentManager.popBackStack();
    }
}