package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {
    ButtonClickListener buttonClickListener = new ButtonClickListener();
    Button registerButton;
    Button cancelButton;

    TextView instrTextView;

    boolean isRegisterPressed = false;
    boolean isCancelPressed = true;

    Intent resultIntent;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.registerButtonId) {
                isRegisterPressed = true;
            }
            if (v.getId() == R.id.cancelButtonId) {
                isCancelPressed = true;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        registerButton = findViewById(R.id.registerButtonId);
        cancelButton = findViewById(R.id.cancelButtonId);

        registerButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);

        instrTextView = findViewById(R.id.instrTextViewId);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("instructions")) {
            String instructions = intent.getStringExtra("instructions");
            instrTextView.setText(instructions);
        }

        resultIntent = new Intent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        resultIntent.putExtra("registerStatus", isRegisterPressed);
        resultIntent.putExtra("cancelStatus", isCancelPressed);

        setResult(10, resultIntent);
        finish();
    }
}
