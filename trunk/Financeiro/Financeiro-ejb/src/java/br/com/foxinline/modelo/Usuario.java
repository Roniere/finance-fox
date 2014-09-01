package br.com.foxinline.modelo;

import br.com.foxinline.generico.EntidadeGenerica;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author ronieresousa
 *
 *
 */
@Entity
public class Usuario extends EntidadeGenerica implements Serializable {

    private String nome;
    private String post;
    private String login;
    private String senha;
    @ManyToMany
    private List<Grupo> grupos;

    public Usuario() {
    }

  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.post == null) ? (other.post != null) : !this.post.equals(other.post)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if (this.grupos != other.grupos && (this.grupos == null || !this.grupos.equals(other.grupos))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 17 * hash + (this.post != null ? this.post.hashCode() : 0);
        hash = 17 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 17 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 17 * hash + (this.grupos != null ? this.grupos.hashCode() : 0);
        return hash;
    }

    public static String encryptPassword(String senha) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bs;
            messageDigest.reset();
            bs = messageDigest.digest(senha.getBytes());

            for (int i = 0; i < bs.length; i++) {
                String hexVal = Integer.toHexString(0xFF & bs[i]);
                if (hexVal.length() == 1) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(hexVal);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stringBuilder.toString();
    }
}
