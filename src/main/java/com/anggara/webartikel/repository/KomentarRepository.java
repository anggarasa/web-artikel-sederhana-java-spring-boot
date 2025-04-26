package com.anggara.webartikel.repository;

import com.anggara.webartikel.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    List<Komentar> findByArtikelId(Long artikelId);
}
