package com.teblab.edgar.parser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "exchanges")
@Data
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "unique_name")
    private String uniqueName;
}
