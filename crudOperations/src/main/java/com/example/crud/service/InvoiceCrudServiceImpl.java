package com.example.crud.service;

import com.example.crud.model.dto.InvoiceInfo;
import com.example.crud.model.entity.UserInvoiceEntity;
import com.example.crud.model.response.InvoiceCrudResponse;
import com.example.crud.repository.UserInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceCrudServiceImpl implements InvoiceCrudService{

    private final UserInvoiceRepository userInvoiceRepository;

    @Override
    public InvoiceCrudResponse getInvoiceResponseByUserId(String userId) throws Exception {
        var invoiceDetailsFromDB = userInvoiceRepository.findById(userId);
        if (invoiceDetailsFromDB.isPresent()){
            var invoiceDetails = invoiceDetailsFromDB.get();
            return InvoiceCrudResponse.builder()
                    .userID(invoiceDetails.getUserID())
                    .amount(invoiceDetails.getAmount())
                    .build();
        }
        throw new Exception("User Invoice Not found from database");
    }

    @Override
    public InvoiceCrudResponse createInvoicing(InvoiceInfo invoiceInfo) {
        var userInvoiceEntity = UserInvoiceEntity.builder()
                .userID(invoiceInfo.getUserID())
                .amount(invoiceInfo.getAmount())
                .build();

        userInvoiceRepository.save(userInvoiceEntity);

        return InvoiceCrudResponse.builder()
                .userID(userInvoiceEntity.getUserID())
                .amount(userInvoiceEntity.getAmount())
                .build();
    }

    @Override
    public InvoiceCrudResponse updateInvoiceInfo(String userId, InvoiceInfo invoiceInfo) throws Exception {
        var userInvoiceEntity = userInvoiceRepository.findById(userId)
                .orElseThrow(() -> new Exception("User Not found in db ::" + userId));

        userInvoiceEntity.setAmount(invoiceInfo.getAmount());
        userInvoiceRepository.save(userInvoiceEntity);

        return InvoiceCrudResponse.builder()
                .userID(userInvoiceEntity.getUserID())
                .amount(userInvoiceEntity.getAmount())
                .build();
    }

    @Override
    public void deleteInvoice(String userId) throws Exception {
        var userInvoiceEntity = userInvoiceRepository.findById(userId)
                .orElseThrow(() -> new Exception("User Not found in db ::" + userId));

        userInvoiceRepository.delete(userInvoiceEntity);
    }
}
