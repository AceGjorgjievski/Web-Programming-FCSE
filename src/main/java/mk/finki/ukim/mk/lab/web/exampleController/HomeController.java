package mk.finki.ukim.mk.lab.web.exampleController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/show")
    public String addImage(@RequestParam("image") MultipartFile file,
                           Model model) {
        model.addAttribute("image",file);
        return "home";
    }

}
