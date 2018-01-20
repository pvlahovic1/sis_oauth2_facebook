package hr.foi.oauth2.facebook.service;

import hr.foi.oauth2.facebook.model.dto.DataDto;

public interface DataService {

    void deleteData(int id);
    void saveNewData(DataDto dataDto);

}
