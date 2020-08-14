package com.example.crud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceCrudResponse {
    @JsonProperty("user_id")
    String userID;

    @JsonProperty("amount")
    Long amount;
}
