package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cpol on 29/05/2017.@localhost
 */
@Entity
@Table(name = "post")
public class Post {

    private Long idpost;
    private String titulo;
    private String texto;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_post")
    @SequenceGenerator(name = "seq_post", sequenceName = "seq_post")
    @Column(name = "idpost")
    public Long getIdpost() {
        return idpost;
    }

    public void setIdpost(Long idpost) {
        this.idpost = idpost;
    }

    @Column(name = "titulo", length = 100)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "texto", length = 100)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


}
