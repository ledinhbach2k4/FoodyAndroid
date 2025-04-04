package bach.dev.foody;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import bach.dev.foody.ui.constract.LoginConstract;
import bach.dev.foody.ui.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginConstract.View {
    private LoginConstract.Presenter mPresenter;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        
        initGUI();
        initData();
    }

    private void initData() {
        mPresenter = new LoginPresenter();
        mPresenter.setView(this);
    }

    private void initGUI() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {
            mPresenter.login(edtEmail.getText().toString(), edtPassword.getText().toString());
        });
    }

    @Override
    public void showErrorMessage(String message) {
        
    }

    @Override
    public void showSuccessMessage(String message) {

    }
}