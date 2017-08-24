package com.opipo.rev.lorewiki.controller;

import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractCRUDController<T, I extends Serializable> {

    protected abstract ServiceDTOInterface<T, I> getService();

    protected abstract Function<T, I> getIdFromElement();

    public boolean checkIdFromElement(I id, T element) {
        return id == getIdFromElement() || id == element || (id != null && element != null && getIdFromElement().apply(element) != null && id.equals(getIdFromElement().apply(element)));
    }

    public boolean hasId(T element) {
        return null != getIdFromElement().apply(element);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "List", notes = "list all the elements")
    public @ResponseBody
    ResponseEntity<Collection<I>> list() {
        return new ResponseEntity<Collection<I>>(getService().findAll().stream().map(getIdFromElement()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get", notes = "Get one element by id")
    public @ResponseBody
    ResponseEntity<T> get(
            @ApiParam(value = "The identifier of the element", required = true) @PathVariable("id") I id) {
        return new ResponseEntity<T>(getService().find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update", notes = "Update one element given the element")
    public @ResponseBody
    ResponseEntity<T> save(
            @ApiParam(value = "The identifier of the element", required = true) @PathVariable("id") I id,
            @ApiParam(value = "Element to update with the changes", required = true) @RequestBody T element) {
        Assert.isTrue(checkIdFromElement(id, element), "The id is not the expected");
        Assert.notNull(getService().find(id), "The element doesn't exist");
        return new ResponseEntity<T>(getService().save(element), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "Create", notes = "Create a element if you know the id")
    public @ResponseBody
    ResponseEntity<T> create(@PathVariable("id") I id,
                             @ApiParam(value = "Element to create if you want to save it directly", required = false) @RequestBody(required = false) T element) {
        Assert.isNull(getService().find(id), "The element exists now");
        T result;
        if (element == null || !hasId(element)) {
            result = getService().create(id);
        } else {
            Assert.isTrue(checkIdFromElement(id, element), "The id is not the expected");
            result = getService().save(element);
        }
        return new ResponseEntity<T>(result, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete", notes = "Delete element by id")
    public @ResponseBody
    ResponseEntity<I> delete(
            @ApiParam(value = "The identifier of the element", required = true) @PathVariable("id") I id) {
        getService().delete(id);
        return new ResponseEntity<I>(id, HttpStatus.OK);
    }
}
