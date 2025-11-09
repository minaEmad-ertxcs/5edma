package com.mina.khedma.repo;


import com.mina.khedma.DAO.TokenDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<TokenDAO, Integer> {

    @Query(value = """
            select t from TokenDAO t inner join UserDAO u
            on t.user.id = u.id
            where u.id = :id and (t.expired = false or t.revoked = false)
            """)
    List<TokenDAO> findAllValidTokenByUser(Integer id);

    Optional<TokenDAO> findByToken(String token);
}
