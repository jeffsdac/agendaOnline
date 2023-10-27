package br.com.fiap.epicontacts.contact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService service;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("contacts", service.findAll());
        return "contact/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        service.delete(id);
        redirect.addFlashAttribute("success", "Contato apagada");
        return "redirect:/contact";
    }

    @GetMapping("new")
    public String form(Contact contact ){
        return "contact/form";
    }

    @PostMapping
    public String create(@Valid Contact contact, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "contact/form";
        service.create(contact);
        redirect.addFlashAttribute("success", "Contato criado com sucesso");
        return "redirect:/contact";
    }
    
}
