package ra.academy.dao.impl.account;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.academy.dao.IAccountDao;
import ra.academy.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountDao implements IAccountDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManager entityManager;

    // hibernate có ngôn ngữ truy vấn riêng HQL
    @Override
    public Account findByUserName(String username) {
        TypedQuery<Account> typedQuery = entityManager.createQuery("select A from Account A where email = :email", Account.class);
        typedQuery.setParameter("email", username);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Account> findAll(int limit, int offset) {
        TypedQuery<Account> typedQuery = entityManager.createQuery("select A from Account A", Account.class);
        List<Account> list = typedQuery.getResultList();
        return list.stream().filter(acc -> list.indexOf(acc) >= offset).limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> typedQuery = entityManager.createQuery("select A from Account A", Account.class);
        return typedQuery.getResultList();
    }

    @Override
    public Account findById(Long id) {
        TypedQuery<Account> typedQuery = entityManager.createQuery("select A from Account A where id = :id", Account.class);
        // truyền tham số vào
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public void save(Account account) {
        Session session = sessionFactory.openSession();
        if (account.getId() == null) {
            // thêm mới
            session.save(account);
        } else {
            // cập nhật
            // phải lấy ra đối tượng cũ trước
            Account old = findById(account.getId());
            // cập nhật đối tượng cũ
            old.copy(account);
            session.saveOrUpdate(account);
        }
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.delete(findById(id));
        session.close();
    }
}
