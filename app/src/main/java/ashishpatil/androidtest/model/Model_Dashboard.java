package ashishpatil.androidtest.model;

import android.os.Build;

import ashishpatil.androidtest.Contracts.contract;

/**
 * Created by ashusonu on 23/03/18.
 */

public class Model_Dashboard implements contract.Imodel {
    @Override
    public String getDiviceName() {

        return android.os.Build.MANUFACTURER + " " + Build.ID;
    }

}
