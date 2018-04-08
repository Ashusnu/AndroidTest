package ashishpatil.androidtest.Contracts;

import java.util.List;

import ashishpatil.androidtest.Database.DatabaseHelper;
import ashishpatil.androidtest.entitys.HeartRates;

/**
 * Created by ashusonu on 23/03/18.
 */

public interface contract {


    interface Iview {
        void getDiviceName();

    }

    interface IHistroyView {
        void getDiviceName();

        void setXYValues();
    }


    interface Ipresenter {
        String getDiviceName();

    }

    interface IPresenter_History {
        String getDiviceName();

        List<HeartRates> getHeartData(DatabaseHelper databaseHelper);
    }

    interface Imodel {
        String getDiviceName();
    }

    interface IHistoryModel {
        String getDiviceName();

        List<HeartRates> getHeartData(DatabaseHelper databaseHelper);
    }

}
