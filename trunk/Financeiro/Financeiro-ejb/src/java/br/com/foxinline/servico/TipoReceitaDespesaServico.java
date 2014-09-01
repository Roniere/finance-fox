/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.enums.TipoFinanceiro;
import br.com.foxinline.modelo.TipoReceitaDespesa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class TipoReceitaDespesaServico extends ServicoGenerico<TipoReceitaDespesa> {

    public TipoReceitaDespesaServico() {
        super(TipoReceitaDespesa.class);
    }
    
    public List<TipoReceitaDespesa> autocomplete(String nome, TipoFinanceiro tipoFinanceiro){
        String str = "SELECT trd FROM TipoReceitaDespesa trd "
                + " WHERE trd.nome like"
                + " :nome and trd.principal = true and trd.tipoFinanceiro = :tipoFinanceiro";
        Query query = getEntityManager().createQuery(str);
        query.setParameter("nome", "%"+nome+"%");
        query.setParameter("tipoFinanceiro", tipoFinanceiro);
        return (List<TipoReceitaDespesa>) query.getResultList();
    }
    
    public List<TipoReceitaDespesa> getSubItens(Long id){
        String str = "SELECT sub FROM TipoReceitaDespesa trd JOIN trd.tipoReceitaDespesas sub WHERE trd.id = :id";
        Query query = getEntityManager().createQuery(str);
        query.setParameter("id", id);
        return (List<TipoReceitaDespesa>) query.getResultList();
    }
    
}
