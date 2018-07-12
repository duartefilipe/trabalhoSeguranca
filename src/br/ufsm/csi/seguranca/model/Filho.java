package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "filho")

public class Filho {

    private Long id;
    private String nomeFilho;
    private Pais pais;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_filho")
    @SequenceGenerator(name = "seq_filho", sequenceName = "seq_filho")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nomeFilho", length = 50)
    public String getNomeFilho() {
        return nomeFilho;
    }

    public void setNomeFilho(String nomeFilho) {
        this.nomeFilho = nomeFilho;
    }

    @ManyToOne
    @JoinColumn(name = "id_pais")
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }



}
