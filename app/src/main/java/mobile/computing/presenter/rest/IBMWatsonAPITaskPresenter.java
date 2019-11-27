package mobile.computing.presenter.rest;

import android.os.AsyncTask;

import mobile.computing.model.rest.IBMWatsonApiTask;

public class IBMWatsonAPITaskPresenter {

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

    public interface View {

        void updateUI(String info);
    }
}
