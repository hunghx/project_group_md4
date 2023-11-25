package ra.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.academy.model.Account;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);
    boolean existsByEmail(String email);
}
