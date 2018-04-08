package ashishpatil.androidtest.presenters;

import ashishpatil.androidtest.Contracts.contract;
import ashishpatil.androidtest.model.Model_Dashboard;

/**
 * Created by ashusonu on 23/03/18.
 */

public class Presenter_Dashboard implements contract.Ipresenter {

    private contract.Iview iview;
    private contract.Imodel imodel;

    public Presenter_Dashboard(contract.Iview iview) {

        imodel = new Model_Dashboard();
        this.iview = iview;
    }

    @Override
    public String getDiviceName() {

        return imodel.getDiviceName();
    }

}
