package ro.pub.cs.systems.eim.lab07.googlesearcher.network;

import android.os.AsyncTask;
import android.webkit.WebView;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import ro.pub.cs.systems.eim.lab07.googlesearcher.general.Constants;

public class GoogleSearcherAsyncTask extends AsyncTask<String, Void, String> {

    private WebView googleResultsWebView;

    public GoogleSearcherAsyncTask(WebView googleResultsWebView) {
        this.googleResultsWebView = googleResultsWebView;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(Constants.GOOGLE_INTERNET_ADDRESS + params[0]);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            return httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onPostExecute(String content) {
        googleResultsWebView.loadDataWithBaseURL(
                Constants.GOOGLE_INTERNET_ADDRESS,
                content,
                Constants.MIME_TYPE,
                Constants.CHARACTER_ENCODING,
                null);
    }
}
