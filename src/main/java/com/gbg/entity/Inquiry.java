package com.gbg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inquiries")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inquiry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Auto-generated Primary Key
    private String fullname;
    private String mobile;
    private String email;
    private String commodity;
    private String packing;
    private String delivery;
    private String price;
    private String payment;
    private String quantity;
    private String conditions;
}
