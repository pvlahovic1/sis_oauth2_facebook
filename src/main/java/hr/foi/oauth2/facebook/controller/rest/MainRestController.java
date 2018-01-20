package hr.foi.oauth2.facebook.controller.rest;

import hr.foi.oauth2.facebook.model.dto.DataDto;
import hr.foi.oauth2.facebook.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainRestController {

    private final DataService dataService;

    @GetMapping("/rest/data/delete/{id}")
    @ResponseBody
    public void deleteData(@PathVariable int id) {
        dataService.deleteData(id);
    }

    @PostMapping("/rest/data/new/save")
    @ResponseBody
    public void saveNewData(@RequestBody DataDto dataDto) {
        dataService.saveNewData(dataDto);
    }

}
