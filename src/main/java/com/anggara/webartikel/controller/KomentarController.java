package com.anggara.webartikel.controller;

import com.anggara.webartikel.model.Komentar;
import com.anggara.webartikel.model.User;
import com.anggara.webartikel.service.ArtikelService;
import com.anggara.webartikel.service.KomentarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KomentarController {
    @Autowired
    private KomentarService komentarService;

    @Autowired
    private ArtikelService artikelService;

    @PostMapping("/komentar/add/{artikelId}")
    public String addKomentar(
            @PathVariable("artikelId") Long artikelId,
            @Valid Komentar komentar,
            BindingResult result,
            @AuthenticationPrincipal User user) {
        if (result.hasErrors()) {
            return "redirect:/artikel/show/" + artikelId + "?error=true";
        }

        komentar.setArtikel(artikelService.findById(artikelId).orElseThrow());
        komentar.setUser(user);
        komentarService.save(komentar);
        return "redirect:/artikel/show/" + artikelId;
    }
}
