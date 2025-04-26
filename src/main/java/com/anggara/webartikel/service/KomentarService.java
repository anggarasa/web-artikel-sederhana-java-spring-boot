package com.anggara.webartikel.service;

import com.anggara.webartikel.model.Komentar;
import com.anggara.webartikel.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KomentarService {
    @Autowired
    private KomentarRepository komentarRepository;

    public Komentar save(Komentar komentar) {
        komentar.setTanggalKomentar(LocalDateTime.now());
        return komentarRepository.save(komentar);
    }

    public List<Komentar> findByArtikelId(Long artikelId) {
        return komentarRepository.findByArtikelId(artikelId);
    }
}
