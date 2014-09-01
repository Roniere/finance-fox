/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Banco;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class BancoServico extends ServicoGenerico<Banco> {

    public BancoServico() {
        super(Banco.class);
    }
    
    public List<Banco> autocomplete(String nome){
        String sql = "SELECT b FROM Banco b WHERE b.nome like :nome";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }
    
}
