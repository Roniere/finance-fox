package br.com.foxinline.generico;

import br.com.foxinline.modelo.Usuario;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Serviço generico, implementa as acoes padroes de persistencia e consulta de T,
 * prové o acesso ao entityManager
 * @author roniere
 */
public class ServicoGenerico<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entity;

    public ServicoGenerico(Class<T> entity) {
        this.entity = entity;
        
    }

    /**
     * Salva a entidade T, e atribui os valores de data de criação e usuário
     * responsável que fazem parte da entidadegenerica na qual a classe T herda
     * @param entity 
     */
    public void save(T entity) {
        try {
            Class p[] = new Class[1];
            p[0] = Date.class;

            //DATE UPDATE
            Method meth = this.entity.getSuperclass().getMethod("setDataCriacao", p);

            Object arglist[] = new Object[1];
            arglist[0] = new Date();

            meth.invoke(entity, arglist);

            p = new Class[1];
            p[0] = Usuario.class;

            meth = this.entity.getSuperclass().getMethod("setUsuarioCriacao", p);
            arglist = new Object[1];
            arglist[0] = verifySystemUserForLogin();

            meth.invoke(entity, arglist);

        } catch (Exception ex) {
            Logger.getLogger(ServicoGenerico.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        entityManager.persist(entity);
    }

    /**
     * Remove a entidade T, altera o valor ativo para false via reflection 
     * e utiliza o {@link #update } para persistir os dados;
     * @param entity 
     * @see br.com.foxinline.servico.ServicoGenerico#update(java.lang.Object) 
     */
    public void delete(T entity) {
        try {
            
            Class p[] = new Class[1];
            p[0] = Boolean.class;

            //SET ATIVO = FALSE
            Method meth = this.entity.getSuperclass().getMethod("setAtivo", p);

            Object arglist[] = new Object[1];
            arglist[0] = Boolean.FALSE;

            meth.invoke(entity, arglist);

        } catch (Exception ex) {
            Logger.getLogger(ServicoGenerico.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        update(entity);
//        T entityToBeRemoved = entityManager.merge(entity);
//        entityManager.remove(entityToBeRemoved);
    }

    /**
     * Atualiza os dados da entidade T e atribui data e usuário responsável pela 
     * alteração
     * @param entity 
     */
    public void update(T entity) {

        
        try {
            Class p[] = new Class[1];
            p[0] = Date.class;

            //DATE UPDATE
            Method meth = this.entity.getSuperclass().getMethod("setDataAtualizacao", p);

            Object arglist[] = new Object[1];
            arglist[0] = new Date();

            meth.invoke(entity, arglist);

            //USUARIO UPDATE
            p = new Class[1];
            p[0] = Usuario.class;
            meth = this.entity.getSuperclass().getMethod("setUsuarioAtualizacao", p);

            Usuario arglist1[] = new Usuario[1];
            arglist1[0] = verifySystemUserForLogin();

            meth.invoke(entity, arglist1);
        } catch (Exception ex) {
            Logger.getLogger(ServicoGenerico.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.createQuery("CREATE TABLE secretaria_hist("
                    + "  id bigint NOT NULL,"
                    + "ativo boolean,"
                    + "descricao character varying(255),"
                    + "nome character varying(255),"
                    + "nomesecretario character varying(255),"
                    + "sgl character varying(255),"
                    + "versao integer,"
                    + "data_inicial timestamp without time zone,"
                    + "data_final timestamp without time zone,"
                    + "codigo serial NOT NULL,"
                    + "createat timestamp without time zone,"
                    + "updateat timestamp without time zone,"
                    + "usuariocreate_id bigint,"
                    + "usuarioupdate_id bigint,"
                    + "CONSTRAINT secretaria_hist_pkey PRIMARY KEY (codigo )"
                    + ")");
            update(entity);
        }

        entityManager.merge(entity);
        entityManager.flush();
    }

    /**
     * Resgata usuário logado no sistema
     * @return 
     */
    public Usuario verifySystemUserForLogin() {
        try {
            String nome = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

            if (nome != null && !nome.isEmpty()) {
                Usuario usr;

                final String sql = "select u from " + Usuario.class.getSimpleName() + " u where "
                        + "u.login like :nome and u.ativo = true";
                Query query = getEntityManager().createQuery(sql);
                query.setParameter("nome", nome);
                usr = (Usuario) query.getSingleResult();

                return usr;
            }
        } catch (Exception e) {
            Logger.getLogger(ServicoGenerico.class.getName()).log(Level.SEVERE, null, e);

            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * Realiza a busca através do parametro {@link askdja }entityID e retorna entidade
     * @param entityID
     * @return 
     */
    public T find(Long entityID) {
        return entityManager.find(entity, entityID);
    }

    /**
     * Retorna todos os registros da classe T
     * @return 
     */
    public List<T> findAll() {
        return entityManager.createQuery("select e from " + entity.getSimpleName() + " e where e.ativo = true").getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Busca com paginação
     * @param t a classe a ser pesquisada
     * @param first primeiro registro
     * @param pageSize tamanho da página
     * @return 
     */
    public List<T> find(T t, int first, int pageSize) {
        String sql = "select e from " + entity.getSimpleName() + " e where e.ativo = true";

        Query query = entityManager.createQuery(sql).setFirstResult(first).setMaxResults(pageSize);

        return query.getResultList();
    }
    
    /**
     * Retorna o total de registros de T
     * @return 
     */
    public Long quantidadeRegistros() {
        Long result = (Long) entityManager.createQuery("select count(e) from " + entity.getSimpleName() + 
                " e where e.ativo = true").getSingleResult();

        return result != null ? result : 0;
    }
}
