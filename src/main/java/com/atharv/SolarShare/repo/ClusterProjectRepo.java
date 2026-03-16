package com.atharv.SolarShare.repo;

import com.atharv.SolarShare.model.ClusterProject;
import com.atharv.SolarShare.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClusterProjectRepo extends JpaRepository<ClusterProject, Long> {
    List<ClusterProject> findByCityIgnoreCase(String city);
    List<ClusterProject> findByCreatorAndIsFullFalse(UserInfo creator);
    List<ClusterProject> findByIsFullFalse();
}