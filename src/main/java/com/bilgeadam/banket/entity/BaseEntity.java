package com.bilgeadam.banket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class BaseEntity {

    @Id
    private String id;

    private UUID uuid;

    private boolean isDeleted;

    private LocalDateTime creationDate;

    private LocalDateTime updatedDate;

    private String createdBy;

    private String lastModifiedBy;

/*
//  Volkan: Persistence kullanmıyoruz ama security kurulduğu zaman bakılmaya ihtiyaç duyulabileceği için yorum satırında.
    @PrePersist
    protected void onCreate() {
        setUuid(java.util.UUID.randomUUID());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =
                (authentication == null || !authentication.isAuthenticated())
                        ? "anonymous"
                        : authentication.getPrincipal().toString();
        if (getId() == null) {
            setCreatedBy(username);
            setLastModifiedBy(username);
        } else {
            setLastModifiedBy(username);
        }
    }
*/

}
