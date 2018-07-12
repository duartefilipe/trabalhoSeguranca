package br.ufsm.csi.seguranca.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pais")
public class Pais {

    private Long id;
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pais")
    @SequenceGenerator(name = "seq_pais", sequenceName = "seq_pais")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
