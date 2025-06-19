package com.erp.tferp.controllers;

import com.quick.rest.iservices.IQuickApiGeneratorService;
import com.quick.rest.models.request.FullBodyDTO;
import com.quick.rest.models.response.QuickApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quick/template")
public class TemplateControllers {

    @Autowired
    private IQuickApiGeneratorService iQuickApiGeneratorService;

    @PostMapping(value = "full-path", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuickApiResponse> generateFull(@RequestBody FullBodyDTO fullBodyDTO){
        QuickApiResponse quickResponse = new QuickApiResponse();
        if(this.iQuickApiGeneratorService.generateFull(fullBodyDTO)) {
            quickResponse.setMessageResponse("Success");
        } else {
            quickResponse.setMessageResponse("Fail");
        }
        return ResponseEntity.ok(quickResponse);
    }


}
