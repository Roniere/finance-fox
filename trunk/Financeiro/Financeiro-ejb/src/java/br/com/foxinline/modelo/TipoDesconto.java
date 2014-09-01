/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class TipoDesconto implements Serializable {

    @Id
    @SequenceGenerator(sequenceName = "seq_tipodesconto", name = "seq_tipodesconto", allocationSize = 1)
    @GeneratedValue(generator = "seq_tipodesconto", strategy = GenerationType.SEQUENCE)    
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
