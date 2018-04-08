package ashishpatil.androidtest.presenters;

import java.util.List;

import ashishpatil.androidtest.Contracts.contract;
import ashishpatil.androidtest.Database.DatabaseHelper;
import ashishpatil.androidtest.entitys.HeartRates;
import ashishpatil.androidtest.model.ModelHistory;

/**
 * Created by ashusonu on 23/03/18.
 */

public class Presenter_History implements contract.IPresenter_History {

    private contract.IHistroyView iHistroyView;
    private ModelHistory modelHistory;

    public Presenter_History(contract.IHistroyView iHistroyView) {
        this.iHistroyView = iHistroyView;
        modelHistory = new ModelHistory();
    }

    @Override
    public String getDiviceName() {
        return modelHistory.getDiviceName();
    }

    @Override
    public List<HeartRates> getHeartData(DatabaseHelper databaseHelper) {
        return modelHistory.getHeartData(databaseHelper);
    }
}
