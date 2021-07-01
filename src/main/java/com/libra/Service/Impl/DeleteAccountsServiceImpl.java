package com.libra.Service.Impl;

import com.libra.Config.FileUploadUtil;
import com.libra.Config.LibraConstants;
import com.libra.Dao.DeleteAccountsDao;
import com.libra.Models.DeleteAccounts;
import com.libra.Service.DeleteAccountsService;
import com.libra.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeleteAccountsServiceImpl implements DeleteAccountsService {

    @Autowired
    private DeleteAccountsDao deleteAccountsDao;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public void deleteAllAccounts() {
        List<DeleteAccounts> accountsForDelete = getAccountsForDelete();

        if(!accountsForDelete.isEmpty()) {
            for (DeleteAccounts deleteAccounts : accountsForDelete) {
                userService.deleteAccount(deleteAccounts.getUsername());
                FileUploadUtil.deleteImage(deleteAccounts.getUsername() + LibraConstants.ConfigConstants.PNG_EXTENSION);
                deleteAccountsDao.deleteAccountById(deleteAccounts.getId());
            }
        }
    }

    @Override
    public List<DeleteAccounts> getAccountsForDelete() {
        return deleteAccountsDao.getAccountsForDelete();
    }

    @Override
    public void addAccountForDelete(String username) {
        deleteAccountsDao.addAccountForDelete(username);
    }
}
