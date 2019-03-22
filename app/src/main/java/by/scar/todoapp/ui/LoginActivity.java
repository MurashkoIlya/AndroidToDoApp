package by.scar.todoapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import by.scar.todoapp.R;

//TODO:Authorization and DB sync

public class LoginActivity extends AppCompatActivity {
    private EditText mLoginField;
    private EditText mPasswordField;
    private Button mEnterBtn;
    private ImageView mPasswordVisibility;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("APP", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        if(preferences.getBoolean("X-Auth", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        mEnterBtn.setOnClickListener(v->{
            edit.putBoolean("X-Auth", true);
            edit.apply();

            startActivity( new Intent(this, MainActivity.class));
            finish();
        });

        mPasswordVisibility.setOnTouchListener((view,event)->{
            switch ( event.getAction() ) {
                case MotionEvent.ACTION_DOWN:
                    mPasswordField.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case MotionEvent.ACTION_UP:
                    mPasswordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
            }
            return true;
        });

    }

    private void findViews(){
        mLoginField = findViewById(R.id.login_field);
        mPasswordField = findViewById(R.id.password_field);
        mEnterBtn = findViewById(R.id.enter_button);
        mPasswordVisibility = findViewById(R.id.password_visibility);
    }

}
