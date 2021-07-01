package com.libra.Service;

import com.libra.Models.DeleteAccounts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeleteAccountsService {

    @Transactional
    void deleteAllAccounts();

    List<DeleteAccounts> getAccountsForDelete();

    void addAccountForDelete(final String username);

}
