package com.anggara.webartikel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "komentar")
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Komentar tidak boleh kosong")
    private String isi;

    private LocalDateTime tanggalKomentar;

    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
