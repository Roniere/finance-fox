/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Secretaria;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author roniere
 */
@Stateless
public class SecretariaServico extends ServicoGenerico<Secretaria>{

    @EJB
    UsuarioServico usuarioServico;
    
    public SecretariaServico() {
        super(Secretaria.class);
    }
 
}
