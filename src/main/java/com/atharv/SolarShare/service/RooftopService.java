package com.atharv.SolarShare.service;

import com.atharv.SolarShare.model.RooftopListing;

import com.atharv.SolarShare.model.UserInfo;
import com.atharv.SolarShare.model.UserType;
import com.atharv.SolarShare.repo.RooftopRepository;
import com.atharv.SolarShare.repo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RooftopService {

    @Autowired
    private RooftopRepository rooftopRepo;

    @Autowired
    private UserInfoRepository userInfoRepo;

    public RooftopListing createListing(RooftopListing listing, String username) {
        UserInfo owner = userInfoRepo.findByUserUsername(username);
        listing.setOwner(owner);
        return rooftopRepo.save(listing);
    }

    public List<RooftopListing> getAllListings() {
        return rooftopRepo.findAll();
    }

    public void addInterest(Long listingId, String companyUsername) {
        RooftopListing listing = rooftopRepo.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        UserInfo company = userInfoRepo.findByUserUsername(companyUsername);

        if (company.getType() == UserType.SOLAR_COMPANY) {
            if (!listing.getInterestedCompanies().contains(company)) {
                listing.getInterestedCompanies().add(company);
                rooftopRepo.save(listing);
            }
        }
    }

    public List<RooftopListing> getListingsByOwner(String username) {
        UserInfo owner = userInfoRepo.findByUserUsername(username);
        return rooftopRepo.findByOwner(owner);
    }
}
