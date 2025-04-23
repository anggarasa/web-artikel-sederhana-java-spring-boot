package com.anggara.webartikel.repository;

import com.anggara.webartikel.model.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByJudulContainingIgnoreCase(String judul);
}
