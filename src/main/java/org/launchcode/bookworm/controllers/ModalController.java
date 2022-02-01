package org.launchcode.bookworm.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("modals")
public class ModalController {

    @GetMapping("modalAddBookSuccess")
    public String modalAddBookSuccess(@RequestParam("userName") String userName, @RequestParam("title") String title, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("title", title);
        return "modalAddBookSuccess";
    }
}
