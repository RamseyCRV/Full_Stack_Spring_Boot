package com.libra.Repository;

import com.libra.Models.DeleteAccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteAccountsRepository extends JpaRepository<DeleteAccountsModel, Integer> {
}
