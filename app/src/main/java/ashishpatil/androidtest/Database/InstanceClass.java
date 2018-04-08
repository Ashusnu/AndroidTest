package ashishpatil.androidtest.Database;

import android.content.Context;

/**
 * Created by ashusonu on 23/03/18.
 */

public class InstanceClass {

    private static DatabaseHelper databaseHelper;

    public InstanceClass() {

    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }

        return databaseHelper;
    }


}
