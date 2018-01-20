package hr.foi.oauth2.facebook.controller;

import hr.foi.oauth2.facebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String getIndexPage() {
        String url = "index";
        if (isAuthenticated()) {
            url = "redirect:/homepage";
        }
        return url;
    }

    @GetMapping("/homepage")
    public String getHomepage(Model model) {
        model.addAttribute("values", userService.fetchAllData());
        model.addAttribute("user", userService.fetchCurrentUser());

        return "homepage";
    }

    @GetMapping("/data/table")
    public String getHomepageTable(Model model) {
        model.addAttribute("values", userService.fetchAllData());

        return "fragments/homepage_fragment::data_table";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_USER"));
    }
}
