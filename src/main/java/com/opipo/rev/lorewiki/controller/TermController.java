package com.opipo.rev.lorewiki.controller;

import com.opipo.rev.lorewiki.model.Page;
import com.opipo.rev.lorewiki.service.PageService;
import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/term")
@Api(value = "REST API to find the terms")
public class TermController {

    @Autowired
    private PageService pageService;


//    @RequestMapping(value = "/{term}", method = RequestMethod.GET)
//    @ApiOperation(value = "Get", notes = "Get links from term")
//    public @ResponseBody
//    ResponseEntity<String> get(
//            @ApiParam(value = "The term to search", required = true) @PathVariable("term") String term) {
//        return new ResponseEntity<T>(getService().find(id), HttpStatus.OK);
//    }
}
