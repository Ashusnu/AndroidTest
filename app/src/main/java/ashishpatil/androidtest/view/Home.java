package ashishpatil.androidtest.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import ashishpatil.androidtest.R;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);


        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Portrait_Dashboard portrait_dashboard = new Portrait_Dashboard();
            fragmentTransaction.replace(android.R.id.content, portrait_dashboard);
            fragmentTransaction.commit();
        }


    }
}
