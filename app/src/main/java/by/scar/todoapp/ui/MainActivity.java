package by.scar.todoapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.FragmentManager;
import by.scar.todoapp.R;
import by.scar.todoapp.ui.base.BaseActivity;
import by.scar.todoapp.ui.fragments.AddItemFragment;
import by.scar.todoapp.ui.fragments.HomeFragment;
import by.scar.todoapp.ui.fragments.ItemDetalizationFragment;
import by.scar.todoapp.ui.fragments.NotificationFragment;
import by.scar.todoapp.ui.fragments.ToDoListFragment;
import by.scar.todoapp.ui.fragments.base.BaseFragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements ToDoListFragment.OnAddItemClickListener, AddItemFragment.OnCreateItemCallback,
        ToDoListFragment.OnItemClickListener, ItemDetalizationFragment.OnDeleteItemCallback,
        ItemDetalizationFragment.OnEditItemCallback {

    private TextView mLogOutText;
    private ImageButton mLogOutButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragmentInMainFrame(HomeFragment.newInstance(), false);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragmentInMainFrame(ToDoListFragment.newInstance(), false);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragmentInMainFrame(NotificationFragment.newInstance(), false);
                    return true;
            }
            return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragmentInMainFrame(HomeFragment.newInstance(), false);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        findViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLogOutText.setOnClickListener(logOutListener);
        mLogOutButton.setOnClickListener(logOutListener);
    }

    private View.OnClickListener logOutListener = view->{
        SharedPreferences pref = getSharedPreferences("APP", this.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        edit.putBoolean("X-Auth", false);
        edit.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    };

    private void popBackStack(){
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    @Override
    public void onAddClick() {
        replaceFragmentInMainFrame(AddItemFragment.newInstance(0), true);
    }

    @Override
    public void onCreateItem() {
        popBackStack();
    }

    private void findViews(){
        mLogOutButton = findViewById(R.id.logOut_image);
        mLogOutText = findViewById(R.id.logOut_text);
    }

    @Override
    public void onItemClick(long id) {
        replaceFragmentInMainFrame(ItemDetalizationFragment.newInstance(id), true);
    }

    @Override
    public void onDeleteItem() {
        popBackStack();
    }

    @Override
    public void onEditItem(long id) {
        replaceFragmentInMainFrame(AddItemFragment.newInstance(id), true);
    }
}
