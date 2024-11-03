package com.between.challenge.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity(name = "Prices")
@AllArgsConstructor
@NoArgsConstructor
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Company company;

    private Timestamp startDate;
    private Timestamp endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
