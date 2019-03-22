package by.scar.todoapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import by.scar.todoapp.R;
import by.scar.todoapp.ui.base.BaseActivity;
import by.scar.todoapp.ui.fragments.AddItemFragment;
import by.scar.todoapp.ui.fragments.HomeFragment;
import by.scar.todoapp.ui.fragments.NotificationFragment;
import by.scar.todoapp.ui.fragments.ToDoListFragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

//TODO:Bottom navigation

public class MainActivity extends BaseActivity implements ToDoListFragment.OnAddItemClickCallback, AddItemFragment.OnCreateItemCallback {

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

    @Override
    public void onAddClick() {
        replaceFragmentInMainFrame(AddItemFragment.newInstance(), true);
    }

    @Override
    public void onCreateItem() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    private void findViews(){
        mLogOutButton = findViewById(R.id.logOut_image);
        mLogOutText = findViewById(R.id.logOut_text);
    }

}
