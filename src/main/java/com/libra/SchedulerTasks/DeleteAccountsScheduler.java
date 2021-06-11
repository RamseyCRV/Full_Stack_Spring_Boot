package com.libra.SchedulerTasks;

import com.libra.Config.LibraConstants;
import com.libra.Service.DeleteAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Timer for deleting desired accounts. Start every 5 minutes.
 */
@Component
public class DeleteAccountsScheduler {

    @Autowired
    private DeleteAccountsService deleteAccount;

    private static final Logger LOG = Logger.getLogger(String.valueOf(DeleteAccountsScheduler.class));

    private static final int FIVE_MINUTES = 300000;

    @Scheduled(fixedRate = FIVE_MINUTES)
    public void scheduleDeleteAccounts(){
        LOG.info(LibraConstants.ConfigConstants.DELETE_ACCOUNTS_SCHEDULER_START + new Date());

        deleteAccount.deleteAllAccounts();

        LOG.info(LibraConstants.ConfigConstants.DELETE_ACCOUNTS_SCHEDULER_FINISHED + new Date());

    }

}
