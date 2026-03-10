package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private Boolean solucion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private UserEntity autor;

    public Respuesta(String mensaje, Topico topico, UserEntity autor) {
        this.mensaje = mensaje;
        this.topico = topico;
        this.autor = autor;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }

    public void actualizarMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}