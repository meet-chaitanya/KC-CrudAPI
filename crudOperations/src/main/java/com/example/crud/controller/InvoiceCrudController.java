package com.example.crud.controller;

import com.example.crud.model.dto.InvoiceInfo;
import com.example.crud.model.response.CrudResponse;
import com.example.crud.service.InvoiceCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/crud_operations")
@RequiredArgsConstructor
public class InvoiceCrudController {

    private final InvoiceCrudService invoiceCrudService;

    @GetMapping(value = "/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrudResponse> fetchInvoice(@RequestParam("user_id") String userId) throws Exception {
        if (StringUtils.isEmpty(userId)) {
            throw new Exception("user_id is either empty or null");
        }

        var response = invoiceCrudService.getInvoiceResponseByUserId(userId);

        var crudResponse =
                CrudResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(response)
                        .message("success")
                        .build();

        return ResponseEntity.status(crudResponse.getStatus()).body(crudResponse);
    }

    @PostMapping(value = "/invoice",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrudResponse> createInvoice(@RequestBody InvoiceInfo invoiceInfo){
        var response = invoiceCrudService.createInvoicing(invoiceInfo);

        var crudResponse =
                CrudResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(response)
                        .message("success")
                        .build();

        return ResponseEntity.status(crudResponse.getStatus()).body(crudResponse);
    }

    @PutMapping(value = "/invoice",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrudResponse> updateInvoice(@RequestParam("user_id") String userId,
                                                      @RequestBody InvoiceInfo invoiceInfo) throws Exception {
        var response = invoiceCrudService.updateInvoiceInfo(userId, invoiceInfo);

        var crudResponse =
                CrudResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(response)
                        .message("success")
                        .build();

        return ResponseEntity.status(crudResponse.getStatus()).body(crudResponse);
    }

    @DeleteMapping(value = "/invoice",
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrudResponse> deleteMapping(@RequestParam("user_id") String userId) throws Exception {
        invoiceCrudService.deleteInvoice(userId);

        var crudResponse =
                CrudResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(null)
                        .message("success")
                        .build();

        return ResponseEntity.status(crudResponse.getStatus()).body(crudResponse);
    }
}
