package com.anggara.webartikel.service;

import com.anggara.webartikel.model.Artikel;
import com.anggara.webartikel.repository.ArtikelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtikelService {
    @Autowired
    private ArtikelRepository artikelRepository;

    public List<Artikel> findAll() {
        return artikelRepository.findAll();
    }

    public Optional<Artikel> findById(Long id) {
        return artikelRepository.findById(id);
    }

    public Artikel save(Artikel artikel) {
        artikel.setTanggalPublikasi(LocalDateTime.now());
        return artikelRepository.save(artikel);
    }

    public void deleteById(Long id) {
        artikelRepository.deleteById(id);
    }

    public List<Artikel> searchByJudul(String judul) {
        return artikelRepository.findByJudulContainingIgnoreCase(judul);
    }
}
