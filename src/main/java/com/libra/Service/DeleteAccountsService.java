package com.libra.Service;

import com.libra.Models.DeleteAccountsModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeleteAccountsService {

    @Transactional
    void deleteAllAccounts();

    List<DeleteAccountsModel> getAccountsForDelete();

    void addAccountForDelete(final String username);

}
