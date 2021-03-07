package com.example.mynewasyncdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyProgressTask extends AsyncTask<Void, Integer, String> {

    Context ctx;
    ProgressDialog pd;

    public MyProgressTask(Context ct) {
        ctx = ct;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(ctx);
        pd.setTitle("Downloading");
        pd.setMessage("Please Wait...");
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });

        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(1000);
                Log.i("Thread", "Execute " + i);
                publishProgress(i);
            }
            return "Successful";
        } catch (Exception e) {
            Log.i("Exception", e.getMessage());
            return "Failture";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue = values[0];
        pd.setProgress(myValue);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
        pd.dismiss();
    }
}
