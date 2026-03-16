package com.atharv.SolarShare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rooftop_listings")
public class RooftopListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private double areaSquareFt;
    private String city;
    private double expectedRent;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("myListings")
    private UserInfo owner;

    @ManyToMany
    @JoinTable(
            name = "listing_interests",
            joinColumns = @JoinColumn(name = "listing_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<UserInfo> interestedCompanies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cluster_id")
    @JsonIgnoreProperties("contributions")
    private ClusterProject clusterProject;

    private Double lat; // Latitude
    private Double lng; // Longitude
}