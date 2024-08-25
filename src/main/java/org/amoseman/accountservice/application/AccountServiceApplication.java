package org.amoseman.accountservice.application;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import org.amoseman.accountservice.dao.AccountDAO;
import org.amoseman.accountservice.dao.RoleDAO;
import org.amoseman.accountservice.dao.ServiceDAO;

public class AccountServiceApplication extends Application<AccountServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new AccountServiceApplication().run(args);
    }

    @Override
    public void run(AccountServiceConfiguration accountServiceConfiguration, Environment environment) throws Exception {
        AccountDAO accountDAO;
        RoleDAO roleDAO;
        ServiceDAO serviceDAO;
    }
}
