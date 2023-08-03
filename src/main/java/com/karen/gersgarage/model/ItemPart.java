package com.karen.gersgarage.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "items_parts")
public class ItemPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItemsParts;
    private String name;
    private double price;
    private String description;
    private int stock;
}
