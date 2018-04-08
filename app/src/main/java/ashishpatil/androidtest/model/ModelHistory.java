package ashishpatil.androidtest.model;

import android.database.Cursor;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import ashishpatil.androidtest.Contracts.contract;
import ashishpatil.androidtest.Database.DatabaseHelper;
import ashishpatil.androidtest.entitys.HeartRates;

/**
 * Created by ashusonu on 23/03/18.
 */

public class ModelHistory implements contract.IHistoryModel {

    @Override
    public String getDiviceName() {
        return android.os.Build.MANUFACTURER + " " + Build.ID;
    }


    // Get All HeartRate History
    @Override
    public List<HeartRates> getHeartData(DatabaseHelper databaseHelper) {

        Cursor cursor = databaseHelper.getAllData();

        List<HeartRates> heartRatesList = new ArrayList<>();

        while (cursor.moveToNext()) {
            heartRatesList.add(new HeartRates(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return heartRatesList;
    }
}
