package com.atharv.SolarShare.controller;

import com.atharv.SolarShare.model.RooftopListing;
import com.atharv.SolarShare.service.RooftopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin("http://localhost:5173")
public class ListingController {

    @Autowired
    private RooftopService rooftopService;

    @PostMapping("/create")
    public ResponseEntity<?> createListing(@RequestBody RooftopListing listing, Authentication authentication) {
        try {
            String username = authentication.getName();
            RooftopListing savedListing = rooftopService.createListing(listing, username);
            return ResponseEntity.ok(savedListing);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating listing: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<RooftopListing> getAllListings() {
        return rooftopService.getAllListings();
    }

    @PostMapping("/{id}/interest")
    public ResponseEntity<?> expressInterest(@PathVariable Long id, Authentication authentication) {
        rooftopService.addInterest(id, authentication.getName());
        return ResponseEntity.ok("Interest recorded");
    }

    @GetMapping("/my-listings")
    public ResponseEntity<List<RooftopListing>> getMyListings(Authentication authentication) {
        String username = authentication.getName();
        List<RooftopListing> myListings = rooftopService.getListingsByOwner(username);
        return ResponseEntity.ok(myListings);
    }
}
