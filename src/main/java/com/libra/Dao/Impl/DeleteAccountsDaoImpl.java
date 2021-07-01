package com.libra.Dao.Impl;

import com.libra.Dao.DeleteAccountsDao;
import com.libra.Models.DeleteAccounts;
import com.libra.Repository.DeleteAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteAccountsDaoImpl implements DeleteAccountsDao {

    @Autowired
    private DeleteAccountsRepository deleteAccountsRepository;

    @Override
    public void deleteAllAccounts() {
        deleteAccountsRepository.deleteAll();
    }

    @Override
    public List<DeleteAccounts> getAccountsForDelete() {
        return deleteAccountsRepository.findAll();
    }

    @Override
    public void addAccountForDelete(String username) {
        DeleteAccounts deleteAccount = new DeleteAccounts();
        deleteAccount.setUsername(username);
        deleteAccountsRepository.save(deleteAccount);
    }

    @Override
    public void deleteAccountById(int id) {
        deleteAccountsRepository.deleteById(id);
    }
}
