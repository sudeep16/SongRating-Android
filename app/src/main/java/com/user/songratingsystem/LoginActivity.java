package com.user.songratingsystem;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.text.Spannable;
        import android.text.SpannableString;
        import android.text.TextUtils;
        import android.text.style.UnderlineSpan;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    EditText uname, pass;
    Button btnLogin, btnCancel;
    String username, password;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.regUser);
        SpannableString content = new SpannableString("Join Now");
        content.setSpan(new UnderlineSpan(), 0, content.length(),0);
        register.setText(content);

        uname = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        remember = findViewById(R.id.checkBox);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = uname.getText().toString().trim();
                password = pass.getText().toString().trim();



                if (TextUtils.isEmpty(username)) {
                    uname.setError("Enter Username");
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Enter Password");
                }

                if (username.equals("admin") && password.equals("admin")) {

                    if(remember.isChecked()) {

                        SaveToShared();
                    }

                        //Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, username);
                        startActivity(intent);

                }

                else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        }

    private void SaveToShared() {

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.commit();

        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
    }
}

