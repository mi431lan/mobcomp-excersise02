package mobile.computing.model.rest;

import android.os.AsyncTask;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

public class RestTask extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        test();
        return "a";

    }

    public void test(  ){
        IamAuthenticator authenticator = new IamAuthenticator("xwlFoZ-SLxOmrIGmiirOIqCG6zOzyPKNHVnaccJWgwFD");
        VisualRecognition visualRecognition = new VisualRecognition("2018-03-19", authenticator);
        visualRecognition.setServiceUrl("https://gateway.watsonplatform.net/visual-recognition/api");

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .url("https://ibm.biz/BdzLPG")
                .build();
        ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();
        System.out.println(
                "\n******** Classify with the General model ********\n" + result
                        + "\n******** END Classify with the General model ********\n");
    }
}
