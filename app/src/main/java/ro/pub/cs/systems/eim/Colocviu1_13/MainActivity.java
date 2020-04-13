package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private Button northButton;
    private Integer northButtonCount;
    private Button southButton;
    private Integer southButtonCount;
    private Button eastButton;
    private Integer eastButtonCount;
    private Button westButton;
    private Integer westButtonCount;
    private Button navigateButton;

    public static final int SECONDARY_ACTIVITY = 10;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.northButtonId) {
                textView.setText(textView.getText() + " " + Constants.NORTH);

                northButtonCount++;
            }
            if (v.getId() == R.id.southButtonId) {
                textView.setText(textView.getText() + " " + Constants.SOUTH);

                southButtonCount++;
            }
            if (v.getId() == R.id.eastButtonId) {
                textView.setText(textView.getText() + " " + Constants.EAST);

                eastButtonCount++;
            }
            if (v.getId() == R.id.westButtonId) {
                textView.setText(textView.getText() + " " + Constants.WEST);

                westButtonCount++;
            }
            if (v.getId() == R.id.navigateButtonId) {
                Intent secondaryActivityIntent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                secondaryActivityIntent.putExtra("instructions", textView.getText().toString());

                startActivityForResult(secondaryActivityIntent, SECONDARY_ACTIVITY);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("textView", textView.getText().toString());
        savedInstanceState.putInt("northButtonCount", northButtonCount);
        savedInstanceState.putInt("southButtonCount", southButtonCount);
        savedInstanceState.putInt("eastButtonCount", eastButtonCount);
        savedInstanceState.putInt("westButtonCount", westButtonCount);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SECONDARY_ACTIVITY) {
                Boolean registerStatus = data.getBooleanExtra("registerStatus", false);
                Boolean cancelStatus = data.getBooleanExtra("cancelStatus", false);

                Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                if (registerStatus)
                    Toast.makeText(this, "Register button was pressed", Toast.LENGTH_LONG).show();
                if (cancelStatus)
                    Toast.makeText(this, "Cancel button was pressed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewId);
        northButton = findViewById(R.id.northButtonId);
        southButton = findViewById(R.id.southButtonId);
        eastButton = findViewById(R.id.eastButtonId);
        westButton = findViewById(R.id.westButtonId);
        navigateButton = findViewById(R.id.navigateButtonId);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);
        navigateButton.setOnClickListener(buttonClickListener);

        northButtonCount = 0;
        southButtonCount = 0;
        eastButtonCount = 0;
        westButtonCount = 0;

        /* restore old state */
        if (savedInstanceState != null) {
            String oldTextViewContent = savedInstanceState.getString("textView");

            if (oldTextViewContent != null) {
                textView.setText(oldTextViewContent);
            }

            Integer oldNorthButtonCount = savedInstanceState.getInt("northButtonCount");
            if (oldNorthButtonCount != null) {
                northButtonCount = oldNorthButtonCount;
            }

            Integer oldSouthButtonCount = savedInstanceState.getInt("southButtonCount");
            if (oldSouthButtonCount != null) {
                southButtonCount = oldSouthButtonCount;
            }
            Integer oldEastButtonCount = savedInstanceState.getInt("eastButtonCount");
            if (oldEastButtonCount != null) {
                eastButtonCount = oldEastButtonCount;
            }
            Integer oldWestButtonCount = savedInstanceState.getInt("westButtonCount");
            if (oldWestButtonCount != null) {
                westButtonCount = oldWestButtonCount;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
