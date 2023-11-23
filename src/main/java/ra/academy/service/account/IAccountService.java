package ra.academy.service.account;

import ra.academy.dto.request.FormLogin;
import ra.academy.dto.request.FormRegister;
import ra.academy.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll(int limit, int offset);
    List<Account> findAll();
    Account findById(Long id);
    void register(FormRegister formRegister);
    int delete(Long id);
    Account login(FormLogin formLogin);
    boolean checkExistByEmail(String email);
    boolean checkExistByPhone(String phone);
}
