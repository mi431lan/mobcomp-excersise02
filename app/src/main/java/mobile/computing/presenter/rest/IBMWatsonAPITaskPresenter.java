package mobile.computing.presenter.rest;

import android.os.AsyncTask;

import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import mobile.computing.model.rest.IBMWatsonApiTask;

public class IBMWatsonAPITaskPresenter implements InternetConnectivityListener {

    private static final String TAG = "IBMWatsonAPITaskPres";

    private View view;
    private AsyncTask ibmWatsonApiTask;

    public IBMWatsonAPITaskPresenter(View view) {

        this.view = view;

    }

    public void classifyImage(String url) {
        ibmWatsonApiTask = new IBMWatsonApiTask(view).execute(url);

    }

    public void stop() {

        if (ibmWatsonApiTask != null
                && ibmWatsonApiTask.getStatus() == AsyncTask.Status.RUNNING) {
            ibmWatsonApiTask.cancel(true);
        }

    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

    }

    public interface View {

        void updateUI(String info);
    }
}
