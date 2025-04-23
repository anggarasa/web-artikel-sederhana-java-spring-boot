package com.anggara.webartikel.controller;

import com.anggara.webartikel.model.Artikel;
import com.anggara.webartikel.service.ArtikelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artikel")
public class ArtikelController {
    @Autowired
    private ArtikelService artikelService;

    @GetMapping("/list")
    public String listArtikel(Model model, @RequestParam(value = "search", required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("artikelList", artikelService.searchByJudul(search));
        } else {
            model.addAttribute("artikelList", artikelService.findAll());
        }
        return "artikel/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("artikel", new Artikel());
        return "artikel/create";
    }

    @PostMapping("/create")
    public String createArtikel(@ModelAttribute("artikel") Artikel artikel) {
        artikelService.save(artikel);
        return "redirect:/artikel/list";
    }

    @GetMapping("/show/{id}")
    public String showArtikel(@PathVariable("id") Long id,  Model model) {
        Artikel artikel = artikelService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Artikel ID:" + id)
        );
        model.addAttribute("artikel", artikel);
        return "artikel/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,  Model model) {
        Artikel artikel = artikelService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Artikel ID:" + id)
        );
        model.addAttribute("artikel", artikel);
        return "artikel/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateArtikel(@PathVariable("id") Long id, @ModelAttribute("artikel") Artikel artikel) {
        artikel.setId(id);
        artikelService.save(artikel);
        return "redirect:/artikel/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtikel(@PathVariable("id") Long id) {
        artikelService.deleteById(id);
        return "redirect:/artikel/list";
    }
}
