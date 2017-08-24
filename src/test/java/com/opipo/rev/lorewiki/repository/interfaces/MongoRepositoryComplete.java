package com.opipo.rev.lorewiki.repository.interfaces;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class MongoRepositoryComplete<T extends Object, R extends Serializable> {
    public abstract T buildDocument();
    public abstract T buildNewDocument();
    public abstract R getKey();
    public abstract MongoTemplate getMongoTemplate();
    public abstract org.springframework.data.mongodb.repository.MongoRepository<T,R> getMongoRepository();
    public abstract Function<T,T> changeInfoInPersistedObject();

    @Before
    public void setUp() throws Exception {
        T document = buildDocument();
        getMongoTemplate().insert(document);
        Assertions.assertNotNull(getMongoRepository().findOne(getKey()));
    }

    @Test
    public void update() {
        T persisted = getMongoRepository().findOne(getKey());
        T changed = changeInfoInPersistedObject().apply(buildDocument());
        T actual = getMongoRepository().save(changed);
        Assertions.assertEquals(changed,actual);
        Assertions.assertNotEquals(changed,persisted);

    }

    @Test
    public void save() {
        T document = buildNewDocument();
        T saved = getMongoRepository().save(document);
        Assertions.assertEquals(document,saved);

    }

    @Test
    public void deleteByKey() {
        Boolean previous = getMongoRepository().findOne(getKey())!=null;
        getMongoRepository().delete(getKey());
        Boolean actual = getMongoRepository().findOne(getKey())!=null;
        Assert.assertTrue(previous);
        Assert.assertFalse(actual);

    }
    @Test
    public void deleteByDocument() {
        Boolean previous = getMongoRepository().exists(getKey());
        getMongoRepository().delete(buildDocument());
        Boolean actual = getMongoRepository().exists(getKey());
        Assert.assertTrue(previous);
        Assert.assertFalse(actual);

    }
    @Test
    public void deleteAll() {
        assertNotEquals(0L, getMongoRepository().count());
        getMongoRepository().deleteAll();
        assertEquals(0L, getMongoRepository().count());

    }
    @Test
    public void list() {
        Collection<T> documents = getMongoRepository().findAll();
        assertTrue(documents.stream().anyMatch(p->p.equals(buildDocument())));
    }
}
