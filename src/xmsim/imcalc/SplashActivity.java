package xmsim.imcalc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		String version = "";
		
		try {
			version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			TextView textViewApp = (TextView) findViewById(R.id.textViewApp);
			textViewApp.setText(getString(R.string.app_name) + "  v" + version);
		}

		Thread timer = new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try{
					sleep(1500);
				}catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intentMain);
				}
			}
			
		};
		
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
