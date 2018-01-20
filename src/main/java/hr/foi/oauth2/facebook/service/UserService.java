package hr.foi.oauth2.facebook.service;

import hr.foi.oauth2.facebook.model.Data;
import hr.foi.oauth2.facebook.model.User;

import java.util.Set;

public interface UserService {

    Set<Data> fetchAllData();
    User fetchCurrentUser();

}
