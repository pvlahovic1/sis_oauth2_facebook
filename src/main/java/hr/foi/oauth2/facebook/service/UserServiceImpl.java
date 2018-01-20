package hr.foi.oauth2.facebook.service;

import hr.foi.oauth2.facebook.model.Data;
import hr.foi.oauth2.facebook.model.User;
import hr.foi.oauth2.facebook.repository.DataRepository;
import hr.foi.oauth2.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DataRepository dataRepository;

    @Override
    public Set<Data> fetchAllData() {
        LinkedHashMap<String, String> userDetail = getDetailMap();
        User user = userRepository.findByFbId(userDetail.get("id"));

        Set<Data> data = new HashSet<>();

        if (user == null) {
            processUser(userDetail.get("id"), userDetail.get("name"));
        } else {
            data.addAll(dataRepository.findByUser(user));
        }

        return data;
    }

    @Override
    public User fetchCurrentUser() {
        LinkedHashMap<String, String> userDetail = getDetailMap();
        return userRepository.findByFbId(userDetail.get("id"));
    }

    private void processUser(String id, String name) {
        User user = userRepository.findByFbId(id);

        if(user == null) {
            User newUser = new User();
            newUser.setFbId(id);
            newUser.setName(name);
            userRepository.save(newUser);
        }
    }

    private LinkedHashMap<String, String> getDetailMap() {
        return (LinkedHashMap<String, String>) ((OAuth2Authentication) SecurityContextHolder.getContext()
                .getAuthentication()).getUserAuthentication().getDetails();
    }
}
