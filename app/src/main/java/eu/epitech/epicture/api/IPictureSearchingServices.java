package eu.epitech.epicture.api;

import android.content.Context;

/**
 * Created by oscar on 07/02/2018.
 */

public interface IPictureSearchingServices {
    String SearchContentByName(Context ctxt, String content, int maximum, ISearchingPicturesServicesCallback callback);
    int ClearCache();
}