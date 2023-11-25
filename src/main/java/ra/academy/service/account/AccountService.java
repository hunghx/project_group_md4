package ra.academy.service.account;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IAccountDao;
import ra.academy.dto.request.FormLogin;
import ra.academy.dto.request.FormRegister;
import ra.academy.model.Account;

import java.util.List;
@Service
public class AccountService implements IAccountService{
    @Autowired
    private IAccountDao accountDao;
    @Override
    public List<Account> findAll(int page, int size) {
        return accountDao.findAll(size,page*size);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public void register(FormRegister formRegister) {
        // chuyển đổi thanhf 1 đối tượng Account
        Account  account = new Account(formRegister.getFullName()
                , formRegister.getEmail()
                , BCrypt.hashpw(formRegister.getPassword(),BCrypt.gensalt(12))
                , formRegister.getPhone()
                , formRegister.getAddress());
        accountDao.save(account);
    }

    @Override
    public void delete(Long id) {
         accountDao.delete(id);
    }

    @Override
    public Account login(FormLogin formLogin) {
        Account account = accountDao.findByUserName(formLogin.getUsername());
        if (account != null && BCrypt.checkpw(formLogin.getPassword(),account.getPassword())){
            return account;
        }
        return null;
    }

    @Override
    public boolean checkExistByEmail(String email) {
        return findAll().stream().anyMatch(acc->acc.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public boolean checkExistByPhone(String phone) {
        return findAll().stream().anyMatch(acc->acc.getPhone().equalsIgnoreCase(phone));
    }
}
