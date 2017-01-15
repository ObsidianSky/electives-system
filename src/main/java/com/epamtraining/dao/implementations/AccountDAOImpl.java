package com.epamtraining.dao.implementations;

import com.epamtraining.connection.DataSource;
import com.epamtraining.dao.interfaces.AccountDAO;
import com.epamtraining.entities.Account;
import com.epamtraining.entities.UserType;
import com.epamtraining.exception.DAOLogicalException;

import java.util.List;

/**
 * Account data access object
 * @author Sergey Bondarenko
 */
public class AccountDAOImpl extends AbstractDAOImpl implements AccountDAO {
    public AccountDAOImpl(DataSource userConn) {
        super(userConn);
    }

    @Override
    public List<Account> findAll() throws DAOLogicalException {
            return findAll(set -> new Account(set.getInt("a.id"),
                        set.getString("a.name"),
                        set.getString("a.surname"),
                        set.getString("a.login"),
                        set.getString("a.password"),
                        new UserType(set.getInt("a.user_type_id"),set.getString("ut.user_type"))),
                "SELECT a.id, a.name, a.surname, a.login, a.password, a.user_type_id, ut.user_type\n" +
                        "FROM elective.account a INNER JOIN elective.user_type ut ON ( a.user_type_id = ut.id  )");
    }

    @Override
    public Account findEntityById(Integer id) throws DAOLogicalException {
            return findFirst(set -> new Account(set.getInt("a.id"),
                    set.getString("a.name"),
                    set.getString("a.surname"),
                    set.getString("a.login"),
                    set.getString("a.password"),
                    new UserType(set.getInt("a.user_type_id"),set.getString("ut.user_type"))),
                "SELECT a.id, a.name, a.surname, a.login, a.password, a.user_type_id, ut.user_type FROM elective.account a \n" +
                        "INNER JOIN elective.user_type ut ON ( a.user_type_id = ut.id  ) \n" +
                        "WHERE a.id = ?", id);
    }

    @Override
    public boolean update(Account entity) throws DAOLogicalException {
            return sqlUpdate("UPDATE ACCOUNT SET NAME = ?, SURNAME = ?, LOGIN = ?, PASSWORD = ?, USER_TYPE_ID = ? WHERE ID = ?",
                entity.getName(), entity.getSurname(), entity.getLogin(), entity.getPassword(), entity.getUserType().getId(), entity.getId());
    }

    @Override
    public boolean updateWithoutPassword(Account entity) throws DAOLogicalException {
            return sqlUpdate("UPDATE ACCOUNT SET NAME = ?, SURNAME = ?, LOGIN = ?, USER_TYPE_ID = ? WHERE ID = ?",
                    entity.getName(), entity.getSurname(), entity.getLogin(), entity.getUserType().getId(), entity.getId());
    }

    @Override
    public boolean delete(Integer id) throws DAOLogicalException {
            return sqlUpdate("DELETE FROM ACCOUNT WHERE ID = ?",  id);
    }

    @Override
    public boolean create(Account entity) throws DAOLogicalException {
            return sqlUpdate("INSERT INTO ACCOUNT (NAME, SURNAME, LOGIN, PASSWORD, USER_TYPE_ID) VALUES ( ? , ? , ? , ?, ? )",
                entity.getName(), entity.getSurname(), entity.getLogin(), entity.getPassword(), entity.getUserType().getId() );
    }

    @Override
    public Account findByLoginAndPassword(String login, String password) throws DAOLogicalException {
        return findOne(set -> new Account(set.getInt("a.id"),
                set.getString("a.name"),
                set.getString("a.surname"),
                set.getString("a.login"),
                set.getString("a.password"),
                new UserType(set.getInt("a.user_type_id"),set.getString("ut.user_type"))),
                "SELECT a.id, a.name, a.surname, a.login, a.password, a.user_type_id, ut.user_type FROM elective.account a " +
                                "INNER JOIN elective.user_type ut ON ( a.user_type_id = ut.id  ) \n" +
                                "WHERE a.login = ? AND a.password = ?", login, password);
    }

    @Override
    public List<Account> findAllTeachers() throws DAOLogicalException {
        return findAll(set -> new Account(set.getInt("a.id"),
                set.getString("a.name"),
                set.getString("a.surname"),
                set.getString("a.login"),
                set.getString("a.password"),
                new UserType(set.getInt("a.user_type_id"),set.getString("ut.user_type"))),
                "SELECT a.id, a.name, a.surname, a.login, a.password, a.user_type_id, ut.user_type\n" +
                            "FROM elective.account a INNER JOIN elective.user_type ut ON ( a.user_type_id = ut.id  ) \n" +
                            "WHERE a.user_type_id = 2");
    }
}
