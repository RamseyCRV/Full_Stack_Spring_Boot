package com.libra.Dao;

import com.libra.Models.DeleteAccounts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeleteAccountsDao {

    @Transactional
    void deleteAllAccounts();

    List<DeleteAccounts> getAccountsForDelete();

    void addAccountForDelete(final String username);

    void deleteAccountById(final int id);
}
