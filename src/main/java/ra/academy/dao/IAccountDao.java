package ra.academy.dao;

import ra.academy.model.Account;

public interface IAccountDao extends IGenericDao<Account,Long> {
    Account findByUserName(String username);
}
