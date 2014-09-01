/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.modelo.Receita;
import br.com.foxinline.modelo.TipoReceitaDespesa;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class ReceitaServico extends ServicoGenerico<Receita> {

    public ReceitaServico() {
        super(Receita.class);
    }

    public List<Receita> pesquisarReceita(Receita receita) {
        String sql = "SELECT r FROM Receita r ";

        if (receita.getTipoReceitaDespesaCategoria() != null) {
            sql += " JOIN r.tipoReceitaDespesaCategoria trd";
        }
        if (receita.getTipoReceitaDespesaSubCategoria() != null) {
            sql += " JOIN r.tipoReceitaDespesaSubCategoria trdsub";
        }
        sql += " WHERE ";
        if (receita.getTipoReceitaDespesaCategoria() != null) {
            sql += " trd.id = :tiporeceita and ";
        }
        if (receita.getTipoReceitaDespesaSubCategoria() != null) {
            sql += " trdsub.id = :tiposubreceita and ";
        }
        if (receita.getDataReceita() != null) {
            sql += " r.dataReceita = :data and ";
        }

        sql += " 1 = 1 ";
        Query query = getEntityManager().createQuery(sql);

        if (receita.getTipoReceitaDespesaCategoria() != null) {
            query.setParameter("tiporeceita", receita.getTipoReceitaDespesaCategoria().getId());
        }
        if (receita.getTipoReceitaDespesaSubCategoria() != null) {
            query.setParameter("tiposubreceita", receita.getTipoReceitaDespesaSubCategoria().getId());
        }
        if (receita.getDataReceita() != null) {
            query.setParameter("data", receita.getDataReceita());
        }


        return query.getResultList();
    }

    public BigDecimal getValorReceitaPorDias(Date dataInicial, Date dataFinal, TipoReceitaDespesa tipoReceitaDespesa,
            TipoReceitaDespesa tipoReceitaDespesaSub) {

        String sql = "SELECT SUM(r.valor) FROM Receita r";

        if (tipoReceitaDespesa != null) {
            sql += " JOIN r.tipoReceitaDespesaCategoria tipo ";
        }

        if (tipoReceitaDespesaSub != null) {
            sql += " JOIN r.tipoReceitaDespesaSubCategoria tiposub ";
        }

        sql += " WHERE ";

        if (tipoReceitaDespesa != null) {
            sql += "tipo.id = :tipo and ";
        }

        if (tipoReceitaDespesaSub != null) {
            sql += " tiposub.id = :tiposub and ";
        }

        sql += " r.dataReceita BETWEEN :datainicial AND :datafinal";

        Query query = getEntityManager().createQuery(sql);
        if (tipoReceitaDespesa != null) {
            query.setParameter("tipo", tipoReceitaDespesa.getId());
        }
        query.setParameter("datainicial", dataInicial);
        query.setParameter("datafinal", dataFinal);

        if (tipoReceitaDespesaSub != null) {
            query.setParameter("tiposub", tipoReceitaDespesaSub.getId());
        }


        return (BigDecimal) (query.getSingleResult() == null ? BigDecimal.ZERO : query.getSingleResult());
    }
    
}
