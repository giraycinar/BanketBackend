package com.bilgeadam.banket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseAPIGroup {
    private UUID uuid;
    private String createDate;
    private String updateDate;
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private Long courseId;
    private Long branchId;
    private List<Long> trainers;
    private Long totalCourseHours;
}
