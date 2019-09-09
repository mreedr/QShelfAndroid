package com.example.quantifiedself;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddData extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_layout);

		Button submit = (Button) findViewById(R.id.submit);

		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new PostTask().execute();
				
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_add_data, menu);
		return true;
	}

	private class PostTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... urls) {
			EditText title = (EditText) findViewById(R.id.title);
			EditText description = (EditText) findViewById(R.id.description);
			EditText tags = (EditText) findViewById(R.id.tags);
			EditText data = (EditText) findViewById(R.id.data);
			
			// do some checks to make sure everything is filled in

			postData(title.getText().toString(), description.getText()
					.toString(), tags.getText().toString(), data.getText().toString());

			return null;
		}

		protected void onProgressUpdate(Void... progress) {
		}

		protected void onPostExecute(Void result) {
			//dialoge says it sent
			EditText title = (EditText) findViewById(R.id.title);
			EditText description = (EditText) findViewById(R.id.description);
			EditText tags = (EditText) findViewById(R.id.tags);
			EditText data = (EditText) findViewById(R.id.data);
			
			title.setText("");
			description.setText("");
			tags.setText("");
			data.setText("");
		}

		public void postData(String title, String description, String tag, String data) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://quantifiedshelf.com/api/upload.php");

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("title", title));
				nameValuePairs.add(new BasicNameValuePair("description",description));
				nameValuePairs.add(new BasicNameValuePair("tag", tag));
				nameValuePairs.add(new BasicNameValuePair("data", data));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}

	}
}
