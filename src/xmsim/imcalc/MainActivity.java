package xmsim.imcalc;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private double height;
	private double weight;
	private double result;
	private Button btnCalculate;
	private Button btnReset;
	private EditText editTextHeight;
	private EditText editTextWeight;
	private TextView textViewResult;
	private TextView textViewResultDescription;
	private AlertDialog.Builder alertDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		height = 0;
		weight = 0;
		result = 0;
		btnCalculate = (Button) findViewById(R.id.buttonCalculate);
		btnReset = (Button) findViewById(R.id.buttonReset);
		textViewResult = (TextView) findViewById(R.id.textViewResult);
		editTextHeight = (EditText) findViewById(R.id.editTextHeight);
		editTextWeight = (EditText) findViewById(R.id.editTextWeight);
		textViewResultDescription = (TextView) findViewById(R.id.textViewResultDescription);
		alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setPositiveButton(getString(android.R.string.ok), null);
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.create();

		textViewResult.setText("");
		textViewResultDescription.setText("");
		textViewResultDescription.setBackgroundColor(Color.WHITE);

		btnCalculate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textViewResult.setText("");
				textViewResultDescription.setText("");
				textViewResultDescription.setBackgroundColor(Color.WHITE);
				
				String tmp;

				tmp = editTextHeight.getText().toString().trim();
				try {
					height = Double.parseDouble(tmp);
				} catch (NumberFormatException ex) {
					height = -1;
				}

				tmp = editTextWeight.getText().toString().trim();
				try {
					weight = Double.parseDouble(tmp);
				} catch (NumberFormatException ex) {
					weight = -1;
				}

				if (height == -1 && weight == -1) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_params));
					alertDialogBuilder.show();
					return;
				} else if (height == -1) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_param_height));
					alertDialogBuilder.show();
					return;
				} else if (weight == -1) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_param_weight));
					alertDialogBuilder.show();
					return;
				} else if (height == 0 && weight == 0) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_params_values));
					alertDialogBuilder.show();
					return;
				} else if (height == 0) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_param_value_height));
					alertDialogBuilder.show();
					return;
				} else if (weight == 0) {
					alertDialogBuilder.setMessage(getString(R.string.invalid_param_value_weight));
					alertDialogBuilder.show();
					return;
				} else {
					height = height / 100; //to meters
					result = weight / (height * height);
				}

				textViewResult.setText(getString(R.string.result) + " " + String.format("%.1f", result));
				textViewResultDescription.setBackgroundColor(Color.WHITE);
				// set description
				if (result >= 0 && result <= 4.99) {
					textViewResultDescription.setText(getString(R.string.result_thinness3));
					textViewResultDescription.setTextColor(Color.RED);
					textViewResultDescription.setBackgroundColor(Color.YELLOW);
				} else if (result >= 5 && result < 10) {
					textViewResultDescription.setText(getString(R.string.result_thinness2));
					textViewResultDescription.setTextColor(Color.RED);
				} else if (result >= 10 && result < 18.5) {
					textViewResultDescription.setText(getString(R.string.result_thinness1));
					textViewResultDescription.setTextColor(Color.MAGENTA);
				} else if (result >= 18.5 && result < 25) {
					textViewResultDescription.setText(getString(R.string.result_normal));
					textViewResultDescription.setTextColor(Color.BLUE);
				} else if (result >= 25 && result < 30) {
					textViewResultDescription.setText(getString(R.string.result_overweight));
					textViewResultDescription.setTextColor(Color.MAGENTA);
				} else if (result >= 30 && result < 35) {
					textViewResultDescription.setText(getString(R.string.result_obesity1));
					textViewResultDescription.setTextColor(Color.RED);
				} else if (result >= 35 && result < 40) {
					textViewResultDescription.setText(getString(R.string.result_obesity2));
					textViewResultDescription.setTextColor(Color.RED);
					textViewResultDescription.setBackgroundColor(Color.YELLOW);
				} else if (result >= 40) {
					textViewResultDescription.setText(getString(R.string.result_obesity3));
					textViewResultDescription.setTextColor(Color.RED);
					textViewResultDescription.setBackgroundColor(Color.YELLOW);
				}
			}
		});
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editTextHeight.setText("");
				editTextWeight.setText("");
				textViewResult.setText("");
				textViewResultDescription.setText("");
				textViewResultDescription.setBackgroundColor(Color.WHITE);
				editTextHeight.requestFocus();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			Intent intentAbout = new Intent("xmsim.imcalc.ABOUTACTIVITY");
			startActivity(intentAbout);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}

}
