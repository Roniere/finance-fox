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
public class Banco implements Serializable {

    
    @Id
    @SequenceGenerator(sequenceName = "seq_banco", name = "seq_banco", allocationSize = 1)
    @GeneratedValue(generator = "seq_banco", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
