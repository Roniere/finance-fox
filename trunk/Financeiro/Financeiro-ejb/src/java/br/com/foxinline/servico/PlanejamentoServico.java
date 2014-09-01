/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.foxinline.servico;

import br.com.foxinline.generico.ServicoGenerico;
import br.com.foxinline.enums.TipoFinanceiro;
import br.com.foxinline.modelo.Planejamento;
import br.com.foxinline.modelo.Secretaria;
import br.com.foxinline.modelo.TipoReceitaDespesa;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author roniere
 */
@Stateless
public class PlanejamentoServico extends ServicoGenerico<Planejamento> {

    public PlanejamentoServico() {
        super(Planejamento.class);
    }
    
    public List<Planejamento> getPlanejamentos(Secretaria secretaria, Date data){
        String sql = "SELECT p FROM Planejamento p JOIN p.secretaria s "
                + " WHERE EXTRACT(YEAR, p.competencia) =  EXTRACT(YEAR, :current)"
                + " and s.id = :id";
        Query query = getEntityManager().createQuery(sql);
        
        query.setParameter("id", secretaria.getId());
        query.setParameter("current", data);
        return (List<Planejamento>) query.getResultList();
    }

    public BigDecimal valorPlanejamentoPorMes(Date mes, TipoReceitaDespesa trd, TipoReceitaDespesa trdsub, Secretaria s, 
            TipoFinanceiro tipoFinanceiro) {
        String sql = "SELECT SUM(p.valor) FROM Planejamento p ";

        if (trd != null) {
            sql += " JOIN p.tipoReceitaDespesa tipo ";
        }

        if (trdsub != null) {
            sql += " JOIN p.tipoReceitaDespesaSub tiposub ";
        }

        if (s != null) {
            sql += " JOIN p.secretaria s";
        }
        

        sql += " WHERE ";

        if(tipoFinanceiro != null){
            sql += "p.tipoFinanceiro = :tipofinanceiro and ";
        }
        
        if (trd != null) {
            sql += " tipo.id = :tipo AND ";
        }

        if (trdsub != null) {
            sql += " tiposub.id = :tiposub AND ";
        }

        if (s != null) {
            sql += " s.id = :secretaria AND";
        }
        if (mes != null) {
            sql += " p.competencia = :mes AND";
        }
        
        sql += " 1=1 ";

        Query query = getEntityManager().createQuery(sql);

        if (trd != null) {
            query.setParameter("tipo", trd.getId());
        }

        if (trdsub != null) {
            query.setParameter("tiposub", trdsub.getId());
        }
        if (s != null) {
            query.setParameter("secretaria", s.getId());
        }
        if (mes != null) {
            query.setParameter("mes", mes);
        }
        if(tipoFinanceiro != null){
            query.setParameter("tipofinanceiro", tipoFinanceiro);
        }

        System.err.println("CONSULTA PLANEJAMENTO");
        try {
            return (BigDecimal) query.getSingleResult();
        } catch (NoResultException e) {
            return BigDecimal.ZERO;
        } catch (NonUniqueResultException nu) {
            return (BigDecimal) query.getResultList().get(0);
        }
    }
}
