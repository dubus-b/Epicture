package eu.epitech.epicture.api;

import java.util.ArrayList;

/**
 * Created by oscar on 08/02/2018.
 */

public interface ISearchingPicturesServicesCallback {
    public void onSuccess(ArrayList<String> Results);
    public void onError();
}
