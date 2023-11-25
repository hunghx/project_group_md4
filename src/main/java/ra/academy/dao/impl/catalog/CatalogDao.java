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
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CatalogDao implements ICatalogDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
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
        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction(); // tạo 1 phiên giao dịch
        if (catalog.getId() == null) {
            // thêm mới
            session.saveOrUpdate(catalog);
        } else {
            // cập nhật
            // phải lấy ra đối tượng cũ trước
            Catalog old = findById(catalog.getId());
            // cập nhật đối tượng cũ
            old.copy(catalog); // commit sự thay đổi
            session.saveOrUpdate(old);
        }
        tran.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        session.delete(findById(id));
        tran.commit();
        session.close();
    }
}
