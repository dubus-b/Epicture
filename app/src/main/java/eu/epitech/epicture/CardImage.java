package eu.epitech.epicture;

import android.os.Parcel;
import android.os.Parcelable;
import eu.epitech.epicture.database.table.*;

/**
 * Created by Louis Giraud on 10/02/2018.
 */

public class CardImage implements Parcelable {

    private String url;
    private int favorite;

    public CardImage(String url) {
        this.url = url;
        this.favorite = dbFavorite.checkFavorite(url);
    }

    public CardImage(String url, int favorite) {
        this.url = url;
        this.favorite = favorite;
    }

    protected CardImage(Parcel in) {
        url = in.readString();
        favorite = in.readInt();
    }

    public static final Creator<CardImage> CREATOR = new Creator<CardImage>() {
        @Override
        public CardImage createFromParcel(Parcel in) {
            return new CardImage(in);
        }

        @Override
        public CardImage[] newArray(int size) {
            return new CardImage[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFavorite() {
        return favorite;
    }

    public void switchFavorite() {
        if (this.favorite == 0) {
            dbFavorite.addUrl(this.url);
            this.favorite = 1;
        } else if (this.favorite == 1) {
            dbFavorite.delUrl(this.url);
            this.favorite = 0;
        }
    }
}
