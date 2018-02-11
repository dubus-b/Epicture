package eu.epitech.epicture.api;

import android.content.Context;

import java.util.LinkedList;

/**
 * Created by oscar on 06/02/2018.
 */

public interface IPictureServices {
    LinkedList<String> SearchContentByName(Context ctxt, String ContentName, int MaximumResults);
    Boolean UploadImage(String FileLocation);
}
