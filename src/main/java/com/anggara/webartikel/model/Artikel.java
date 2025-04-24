package com.anggara.webartikel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "artikel")
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    @Size(min = 3, message = "Judul minimal 3 karakter")
    private String judul;

    @NotBlank(message = "Konten tidak boleh kosong")
    @Size(min = 10, message = "Konten minimal 10 karakter")
    private String konten;

    @NotBlank(message = "Penulis tidak boleh kosong")
    private String penulis;

    private LocalDateTime tanggalPublikasi;
}
