package com.anggara.webartikel.controller;

import com.anggara.webartikel.model.Artikel;
import com.anggara.webartikel.model.Komentar;
import com.anggara.webartikel.service.ArtikelService;
import com.anggara.webartikel.service.KomentarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/artikel")
public class ArtikelController {
    @Autowired
    private ArtikelService artikelService;

    @Autowired
    private KomentarService komentarService;
    @Autowired
    private View error;

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
    public String createArtikel(@Valid @ModelAttribute("artikel") Artikel artikel, BindingResult result) {
        if (result.hasErrors()) {
            return "artikel/create"; // Kembali ke form jika ada error
        }
        artikelService.save(artikel);
        return "redirect:/artikel/list";
    }

    @GetMapping("/show/{id}")
    public String showArtikel(@PathVariable("id") Long id,  Model model) {
        Artikel artikel = artikelService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Artikel ID:" + id)
        );
        model.addAttribute("artikel", artikel);
        model.addAttribute("komentarList", komentarService.findByArtikelId(id));
        model.addAttribute("newKomentar", new Komentar());
        if (error != null) {
            model.addAttribute("errorMessage", "Komentar tidak boleh kosong");
        }
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
    public String updateArtikel(@PathVariable("id") Long id, @Valid @ModelAttribute("artikel") Artikel artikel, BindingResult result) {
        if (result.hasErrors()) {
            return "artikel/edit"; // Kembali ke form jika ada error
        }
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
