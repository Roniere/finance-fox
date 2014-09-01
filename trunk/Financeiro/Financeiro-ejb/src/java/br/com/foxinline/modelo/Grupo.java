/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author ely
 *
 * Grupos
 */
@Entity
public class Grupo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_groups", sequenceName = "seq_groups", allocationSize = 1)
    @GeneratedValue(generator = "seq_groups", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Nome do grupo contendo epenas letras(a-z) e underlines(_), separando as
     * palavras
     */
    private String nome;
    /**
     * Descrição o grupo contendo letras (A-Z, a-z), espaços e acentos.
     * <br/>Este campo deve ser usado para exibição do grupo
     */
    private String descricao;
    @ManyToMany(mappedBy = "grupos")
    private List<Usuario> usuarios;

    public Grupo() {
    }

    public Grupo(Long id, String nome, List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.usuarios = usuarios;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 67 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Grupo{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + '}';
    }
}
