package mobile.computing.model.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

import mobile.computing.presenter.rest.IBMWatsonAPITaskPresenter;

public class IBMWatsonApiTask extends AsyncTask<String, String, String> {

    private static final String TAG = "IBMWatsonApiTask";
    private static final String API_KEY = "xwlFoZ-SLxOmrIGmiirOIqCG6zOzyPKNHVnaccJWgwFD";
    private static final String SERVICE_URL = "https://gateway.watsonplatform.net/visual-recognition/api";
    private static final String VERSION_DATE = "2018-03-19";
    private IBMWatsonAPITaskPresenter.View view;
    // check if internet wenn ja request ausführen
    // wenn nein parameter speichern und
    // request ausführen sobald internet wieder verfügbar
    //https://medium.com/@ankit_aggarwal/check-active-internet-connection-on-android-device-3138ad81932d
    //https://developer.android.com/topic/libraries/architecture/workmanager/basics.html
    // persistence with sqlite o.ä

    public IBMWatsonApiTask(IBMWatsonAPITaskPresenter.View view) {
        this.view = view;
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.d(TAG, "Performing Async IBMWatsonApiTask...");

        return classifyImage(strings[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        view.updateUI(s);
    }

    private String classifyImage(String url) {

        VisualRecognition visualRecognition = authenticate();

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .url(url)
                .build();
        ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();
        Log.d(TAG, "API call was successful.");
        Log.d(TAG, "Result: " + result);

        return ("\n******** Classify with the General model ********\n" + result
                + "\n******** END Classify with the General model ********\n");
    }

    private VisualRecognition authenticate() {

        IamAuthenticator authenticator = new IamAuthenticator(API_KEY);
        VisualRecognition visualRecognition = new VisualRecognition(VERSION_DATE, authenticator);
        visualRecognition.setServiceUrl(SERVICE_URL);
        Log.d(TAG, "Authentication was successful.");

        return visualRecognition;
    }

}
