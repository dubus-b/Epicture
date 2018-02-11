package eu.epitech.epicture.api;

import java.util.ArrayList;

/**
 * Created by oscar on 07/02/2018.
 */

public interface IUserManager {
    boolean AddUser();
    void DeleteUser(int UserId);
    User GetUser(int UserId);
    ArrayList<User> GetUsers();
}
