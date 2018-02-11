package eu.epitech.epicture.api;

import java.util.Date;

/**
 * Created by oscar on 07/02/2018.
 */

public class User {
    private int _id;
    private Date _subcription_date;
    private String _unique_str;
    private String _platform;

    public String get_unique_str() {
        return _unique_str;
    }

    public void set_unique_str(String _unique_str) {
        this._unique_str = _unique_str;
    }

    public User(int id, Date date, String unique, String platform) {
        _id = id;
        _subcription_date = date;
        _unique_str = unique;
        _platform = platform;
    }

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

    public void set_platform(String platform) {
        _platform = platform;
    }

    public String get_platfom() {
        return _platform;
    }
}
