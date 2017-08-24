package com.opipo.rev.lorewiki.controller.interfaces;

import com.opipo.rev.lorewiki.controller.AbstractCRUDController;
import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public interface GenericControllerTest<T, I extends Serializable> {

    AbstractCRUDController<T,I> getController();
    ServiceDTOInterface<T,I> getService();
    T buildElement(I id);
    I getCorrectID();
    I getIncorrectID();

    default boolean validateResponseEntity(ResponseEntity<?> response, HttpStatus statusExpected, Object valueExpected) {
        assertNotNull(response);
        assertEquals(statusExpected, response.getStatusCode());
        assertEquals(valueExpected, response.getBody());
        return true;
    }
}
