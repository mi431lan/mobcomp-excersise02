package mobile.computing.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import mobile.computing.presenter.rest.IBMWatsonAPITaskPresenter;
import mobile.computing.project.R;

public class MainActivity extends AppCompatActivity implements IBMWatsonAPITaskPresenter.View {

    private static final String TAG = "MainActivity";
    IBMWatsonAPITaskPresenter ibmWatsonAPITaskPresenter;

    Button testButton;
    TextView apiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibmWatsonAPITaskPresenter = new IBMWatsonAPITaskPresenter(this);
        apiResult = findViewById(R.id.apiResult);
        apiResult.setMovementMethod(new ScrollingMovementMethod());
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d(TAG, "clicked");
                ibmWatsonAPITaskPresenter.classifyImage("https://ibm.biz/BdzLPG");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ibmWatsonAPITaskPresenter.stop();
    }

    @Override
    public void updateUI(String result) {
        apiResult.setText(result);
    }
}
