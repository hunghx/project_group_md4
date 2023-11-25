package ra.academy.dao.impl.catalog;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.academy.dao.ICatalogDao;
import ra.academy.model.Account;
import ra.academy.model.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CatalogDao implements ICatalogDao {
//    @Autowired
//    private SessionFactory sessionFactory;
//    @Autowired
//    private EntityManager entityManager;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Catalog> findAll(int limit, int offset) {
        TypedQuery<Catalog> typedQuery = entityManager.createQuery("select A from Catalog A", Catalog.class);
        List<Catalog> list = typedQuery.getResultList();
        return list.stream().filter(acc -> list.indexOf(acc) >= offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Catalog> findAll() {
        TypedQuery<Catalog> typedQuery = entityManager.createQuery("select A from Catalog A", Catalog.class);
        return typedQuery.getResultList();
    }

    @Override
    public Catalog findById(Long id) {
        TypedQuery<Catalog> typedQuery = entityManager.createQuery("select A from Catalog A where id =:id", Catalog.class);
        typedQuery.setParameter("id",id);
        return typedQuery.getSingleResult();
    }

    @Override
    public void save(Catalog catalog) {

        if (catalog.getId() == null) {
            // thêm mới
         entityManager.persist(catalog);
        } else {
            // cập nhật
            entityManager.merge(catalog);
        }

    }

    @Override
    public void delete(Long id) {
       entityManager.remove(findById(id));
    }
}
