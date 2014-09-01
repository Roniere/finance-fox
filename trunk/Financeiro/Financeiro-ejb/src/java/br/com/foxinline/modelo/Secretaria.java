/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import br.com.foxinline.generico.EntidadeGenerica;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author roniere
 */
@Entity
public class Secretaria  extends EntidadeGenerica implements Serializable{

    private String nome;
    private String nomeSecretario;
    private String sgl;
    private String descricao;
    
    public Secretaria() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSecretario() {
        return nomeSecretario;
    }

    public void setNomeSecretario(String nomeSecretario) {
        this.nomeSecretario = nomeSecretario;
    }

    public String getSgl() {
        return sgl;
    }

    public void setSgl(String sgl) {
        this.sgl = sgl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
