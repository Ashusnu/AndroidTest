package ashishpatil.androidtest.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ashishpatil.androidtest.Contracts.contract;
import ashishpatil.androidtest.Database.InstanceClass;
import ashishpatil.androidtest.R;
import ashishpatil.androidtest.presenters.Presenter_Dashboard;


public class Portrait_Dashboard extends Fragment implements contract.Iview {

    TextView device_name, battery_status;
    ImageView battary_icon;
    LinearLayout vitals;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Presenter_Dashboard presenter_dashboard;
    // Battery icon change on battery status change
    BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            if (level == 100) {
                battary_icon.setImageResource(R.drawable.ic_battery_full_black_24dp);
            } else if (level < 100 && level > 90) {
                battary_icon.setImageResource(R.drawable.ic_battery_80_black_24dp);
            } else if (level > 60 && level < 90) {
                battary_icon.setImageResource(R.drawable.ic_battery_80_black_24dp);
            } else if (level > 30 && level < 60) {
                battary_icon.setImageResource(R.drawable.ic_battery_50);
            } else if (level > 20 && level < 30) {
                battary_icon.setImageResource(R.drawable.ic_battery_50);
            } else if (level > 15 && level < 20) {
                battary_icon.setImageResource(R.drawable.ic_battery_20);
            } else if (level < 15) {
                battary_icon.setImageResource(R.drawable.ic_battery_15);
            }

            battery_status.setText(level + " %");

        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup vg,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portrait_dashboard, vg, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        init();

        // Broadcast receiver for battery status
        IntentFilter intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(mBatInfoReceiver, intentfilter);

        getDiviceName();
        insertData();


        vitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                HistroyFragment histroyPage = new HistroyFragment();
                fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,
                        R.animator.fragment_slide_right_exit);
                fragmentTransaction.replace(android.R.id.content, histroyPage);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    private void init() {

        device_name = getView().findViewById(R.id.device_name);
        battery_status = getView().findViewById(R.id.battery_status);
        battary_icon = getView().findViewById(R.id.battery_icon);
        vitals = getView().findViewById(R.id.vitals);

        presenter_dashboard = new Presenter_Dashboard(this);
    }

    private void insertData() {

        Context context = getActivity().getApplicationContext();
        InstanceClass.getDatabaseHelper(context).deleteAll();

        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "1:00", "75");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "1:20", "60");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "1:40", "65");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "2:00", "62");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "2:20", "70");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "2:30", "77");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "2:40", "65");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "3:00", "70");
        InstanceClass.getDatabaseHelper(context).insertData("23 Feb 2013", "3:20", "74");

    }

    @Override
    public void getDiviceName() {
        device_name.setText(presenter_dashboard.getDiviceName());
    }


}