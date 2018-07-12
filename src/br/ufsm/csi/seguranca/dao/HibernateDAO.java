package br.ufsm.csi.seguranca.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufsm.csi.seguranca.model.Funcionario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.Map;

/**
 * Created by cpol on 31/05/2017.
 */
@Repository
public class HibernateDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void criaObjeto(Object o) {
        sessionFactory.getCurrentSession().save(o);
    }

    public void removeObjeto(Object o) {
        sessionFactory.getCurrentSession().remove(o);
    }

    public Collection listaObjetos(Class classe, Map<String, String> likeMap, Integer maxResults, String propOrdem, boolean asc) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classe);
        detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Map.Entry<String, String> ent : likeMap.entrySet()) {
            detachedCriteria.add(Restrictions.ilike(ent.getKey(), "%" + ent.getValue() + "%"));
        }
        if (propOrdem != null) {
            if (asc) {
                detachedCriteria.addOrder(Order.asc(propOrdem));
            } else {
                detachedCriteria.addOrder(Order.desc(propOrdem));
            }
        }
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }
        return criteria.list();
    }


    public Collection<Object> listaObjetosEquals(Class classe, Map<String, Object> equalsMap) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classe);
        detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Map.Entry<String, Object> ent : equalsMap.entrySet()) {
            detachedCriteria.add(Restrictions.eq(ent.getKey(), ent.getValue()));
        }

        return detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
    }

    public Object carregaObjeto(Class classe, Serializable id) {
        return sessionFactory.getCurrentSession().get(classe, id);
    }

    public Funcionario findUsuario(String login, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        System.out.println("ta aqui: "+login);
        byte[] senhaSHA = md.digest(senha.getBytes("ISO-8859-1"));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Funcionario.class);
        detachedCriteria.add(Restrictions.eq("login", login));
        detachedCriteria.add(Restrictions.eq("senha", senhaSHA));
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.setMaxResults(10);
        return (Funcionario) criteria.uniqueResult();
    }

}