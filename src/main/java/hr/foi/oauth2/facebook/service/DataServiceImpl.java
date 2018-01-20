package hr.foi.oauth2.facebook.service;

import hr.foi.oauth2.facebook.model.Data;
import hr.foi.oauth2.facebook.model.User;
import hr.foi.oauth2.facebook.model.dto.DataDto;
import hr.foi.oauth2.facebook.repository.DataRepository;
import hr.foi.oauth2.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final DataRepository dataRepository;
    private final UserRepository userRepository;

    @Override
    public void deleteData(int id) {
        LinkedHashMap<String, String> userData = getDetailMap();

        User user = userRepository.findByFbId(userData.get("id"));

        Set<Data> data = dataRepository.findByUser(user);

        Set<Data> result = data.stream().filter(d -> d.getId() == id).collect(Collectors.toSet());

        if (!result.isEmpty()) {
            dataRepository.delete(id);
        }

    }

    @Override
    public void saveNewData(DataDto dataDto) {
        LinkedHashMap<String, String> userData = getDetailMap();

        User user = userRepository.findByFbId(userData.get("id"));

        Data data = new Data();
        data.setTitle(dataDto.getTitle());
        data.setValue(dataDto.getValue());
        data.setDate(LocalDateTime.now());
        data.setUser(user);

        dataRepository.save(data);
    }

    private LinkedHashMap<String, String> getDetailMap() {
        return (LinkedHashMap<String, String>) ((OAuth2Authentication) SecurityContextHolder.getContext()
                .getAuthentication()).getUserAuthentication().getDetails();
    }
}
