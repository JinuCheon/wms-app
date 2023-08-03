package com.dope.wmsapp.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 참조무결성 해제.
 * increament pk 1부터 시작하도록..
 * 해당 코드에 대한 설명
 * https://youtu.be/tEcRdRZ0x2U?t=5150
 */

@Component
public class DatabaseCleaner implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;
    private Set<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        tableNames = entityManager.getMetamodel().getEntities().stream()
                .filter(e -> e.getJavaType().isAnnotationPresent(Table.class))
                .map(e -> e.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toUnmodifiableSet());
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName + " RESTART IDENTITY ").executeUpdate();
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
