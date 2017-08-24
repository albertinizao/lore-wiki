package com.opipo.rev.lorewiki.service.interfaces;

import com.opipo.rev.lorewiki.service.impl.AbstractServiceDTO;
import org.mockito.Mockito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface GenericInterface<T extends Object, I extends Serializable> {
    MongoRepository<T,I> getRepository();
    AbstractServiceDTO<T, I> getService();
    T buildElement(I id);
    I getCorrectID();

    default void initFindCorrect(I id) {
        Mockito.when(getRepository().findOne(id)).thenReturn(buildElement(id));
    }
}
