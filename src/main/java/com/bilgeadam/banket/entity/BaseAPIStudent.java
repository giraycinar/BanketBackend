package com.bilgeadam.banket.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BaseAPIStudent {

    private UUID uuid;

    private String createDate;

    private String updateDate;

    private Long id;

    private Long groupId;

    private Long branchId;

    private String name;

    private String surname;

    private String personalEmail;

    private String baEmail;

    private String baBoostEmail;

    private String phoneNumber;

    private String address;

    private String school;

    private String department;

    private String birthDate;

    private String birthPlace;

    /*  Volkan: BaseAPIdan alınan bu satırlara şu anda bir ihtiyaç yok.
    private EStatus status = EStatus.ACTIVE;

    private EStatus internShipStatus = EStatus.PASSIVE;
    */

    private String courseName;

    private String saleDate;

}
