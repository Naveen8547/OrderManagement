package com.example.orders.discountSchemes;

import com.example.orders.auditable.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "Discount")
public class DiscountSchemes extends Auditable<String> {

    @Id
    @Column(name = "scheme_id")
    @GeneratedValue
    private Long schemeId;

    @Column(name = "range", unique = true)
    private Long range;

    @Column(name = "scheme_per")
    private Float schemePer;

    public DiscountSchemes(Long range, Float schemePer) {
        this.range = range;
        this.schemePer = schemePer;
    }

    public DiscountSchemes() {
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public Float getSchemePer() {
        return schemePer;
    }

    public void setSchemePer(Float schemePer) {
        this.schemePer = schemePer;
    }
}
