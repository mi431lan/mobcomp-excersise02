package mobile.computing.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import org.w3c.dom.Text;

import mobile.computing.persistence.sharedpref.SharedPreferencesManager;
import mobile.computing.presenter.rest.IBMWatsonAPITaskPresenter;
import mobile.computing.project.R;

public class MainActivity extends AppCompatActivity implements IBMWatsonAPITaskPresenter.View, InternetConnectivityListener {

    private static final String TAG = "MainActivity";
    private IBMWatsonAPITaskPresenter ibmWatsonAPITaskPresenter;
    private InternetAvailabilityChecker mInternetAvailabilityChecker;
    private SharedPreferencesManager sharedPreferencesManager;

    private Button testButton;
    private TextView apiResult;
    private boolean hasInternet;
    private String exampleImageUrl = "https://ibm.biz/BdzLPG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InternetAvailabilityChecker.init(this);
        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);

        ibmWatsonAPITaskPresenter = new IBMWatsonAPITaskPresenter(this);

        sharedPreferencesManager = new SharedPreferencesManager(this);

        apiResult = findViewById(R.id.apiResult);
        apiResult.setMovementMethod(new ScrollingMovementMethod());
        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                apiResult.setText("");
                Log.d(TAG, "clicked");
                if (hasInternet) {
                    ibmWatsonAPITaskPresenter.classifyImage(exampleImageUrl);

                } else {
                    sharedPreferencesManager.saveString("url", exampleImageUrl);
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ibmWatsonAPITaskPresenter.stop();
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);

    }

    @Override
    public void updateUI(String result) {
        apiResult.setText(result);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        hasInternet = isConnected;

        if (!isConnected) {
            Log.d(TAG, "no internet");
        } else if (isConnected) {
            Log.d(TAG, "internet");
            ibmWatsonAPITaskPresenter.classifyImage(sharedPreferencesManager.readString("url"));

        }
    }
}
