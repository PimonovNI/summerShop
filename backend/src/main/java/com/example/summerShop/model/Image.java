package com.example.summerShop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "img_storage")
public class Image {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "url", unique = true)
    private String url;

}
