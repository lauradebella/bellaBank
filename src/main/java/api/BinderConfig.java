package api;

import api.account.AccountService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

class BinderConfig extends AbstractBinder {
    @Override
    protected void configure() {

        bind(AccountService.class).to(AccountService.class);

        bindFactory(EntityManagerProducer.class)
                .to(EntityManager.class)
                .in(Singleton.class);
    }
}
