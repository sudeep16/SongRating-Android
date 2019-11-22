package com.user.songratingsystem;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    EditText uname, pass;
    Button btnLogin;
    String usernamne, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamne = uname.getText().toString();
                password = pass.getText().toString();

                if (TextUtils.isEmpty(usernamne)) {
                    uname.setError("Enter Username");
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Enter Password");
                }

                if (usernamne.equals("admin") && password.equals("admin")) {
                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                    intent.putExtra(EXTRA_MESSAGE, usernamne);
//                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}

