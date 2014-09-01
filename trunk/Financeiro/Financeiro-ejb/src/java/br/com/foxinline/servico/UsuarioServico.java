/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Usuario;
import java.security.Principal;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class UsuarioServico extends ServicoGenerico<Usuario> {

    public UsuarioServico() {
        super(Usuario.class);
    }

    public Usuario getCurrentUser() {
        try {
            final Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (userPrincipal != null) {
                return verifySystemUserForLogin(userPrincipal.getName());
            }
        } catch (Exception e) {
            
        }
        return null;
    }

    public Usuario verifySystemUserForLogin(String nome) {
        if (nome == null) {
            return null;
        }
        Usuario usr;
        try {
            final String sql = "select u from Usuario u where "
                    + "u.login like :nome";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("nome", nome);
            usr = (Usuario) query.getSingleResult();

        } catch (Exception e) {
            System.err.println("Nenhum usuário encontrado");
            return null;
        }
        return usr;
    }
}
