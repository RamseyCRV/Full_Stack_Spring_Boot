package com.libra.Dao;

import com.libra.Models.DeleteAccountsModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeleteAccountsDao {

    @Transactional
    void deleteAllAccounts();

    List<DeleteAccountsModel> getAccountsForDelete();

    void addAccountForDelete(final String username);

    void deleteAccountById(final int id);
}
