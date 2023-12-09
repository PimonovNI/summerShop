package com.example.summerShop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "size_name", nullable = false, unique = true, length = 16)
    private String name;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    private List<Storage> storages = new ArrayList<>();

}
