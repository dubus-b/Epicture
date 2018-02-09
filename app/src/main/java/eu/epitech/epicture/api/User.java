package eu.epitech.epicture.api;

import java.util.Date;

/**
 * Created by oscar on 07/02/2018.
 */

class User {
    private int _id;
    private Date _subcription_date;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date get_subcription_date() {
        return _subcription_date;
    }

    public void set_subcription_date(Date _subcription_date) {
        this._subcription_date = _subcription_date;
    }
}
