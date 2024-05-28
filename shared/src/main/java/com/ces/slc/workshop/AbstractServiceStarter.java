package com.ces.slc.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = AbstractServiceStarter.PACKAGE_ROOT)
@EnableJpaRepositories(AbstractServiceStarter.PACKAGE_ROOT)
@EntityScan(AbstractServiceStarter.PACKAGE_ROOT)
public abstract class AbstractServiceStarter {

    /**
     * The root package of a service, used for scanning components and entities. This is a high-level
     * package, so it also includes all shared packages.
     */
    public static final String PACKAGE_ROOT = "com.ces.slc.workshop";

    protected static void start(Class<? extends AbstractServiceStarter> serviceClass, String[] args) {
        SpringApplication.run(serviceClass, args);
    }

}
