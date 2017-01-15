package com.epamtraining.service;

import com.epamtraining.dao.DaoFactory;
import com.epamtraining.entities.Account;
import com.epamtraining.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Accounts service
 * @author Sergey Bondarenko
 */
public class AccountService {
    /**
     * Set all accounts for editing
     * @param request
     * @throws ServiceTechnicalException
     * @throws ServiceLogicalException
     */
    public static void setAllAccounts(HttpServletRequest request) throws ServiceTechnicalException, ServiceLogicalException {
        try {
            List<Account> accounts = DaoFactory.getDaoFactory().getAccountDao().findAll();
            request.setAttribute("accounts", accounts);
        } catch (DAOLogicalException e) {
            throw new ServiceLogicalException(e);
        } catch (DAOTechnicalException e) {
            throw new ServiceTechnicalException(e);
        }
    }
}
