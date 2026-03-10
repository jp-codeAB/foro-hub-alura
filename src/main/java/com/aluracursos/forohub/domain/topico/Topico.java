package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private UserEntity autor;

    public Topico(String titulo, String mensaje, UserEntity autor) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.status = "ABIERTO";
    }

    public void actualizarMensaje(String nuevoMensaje) {
        if (nuevoMensaje != null && !nuevoMensaje.isBlank()) {
            this.mensaje = nuevoMensaje;
        }
    }
}