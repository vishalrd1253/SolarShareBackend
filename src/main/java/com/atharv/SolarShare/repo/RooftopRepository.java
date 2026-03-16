package com.atharv.SolarShare.repo;

import com.atharv.SolarShare.model.ClusterProject;
import com.atharv.SolarShare.model.RooftopListing;
import com.atharv.SolarShare.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RooftopRepository extends JpaRepository<RooftopListing, Long> {
    List<RooftopListing> findByCity(String city);
    List<RooftopListing> findByOwner(UserInfo owner);
    List<RooftopListing> findByClusterProject(ClusterProject clusterProject);
}