package src.src.main.java.skypackage.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.src.main.java.skypackage.services.SkyPackage;

import java.util.List;

@Repository
public interface SkyPackageDAO extends JpaRepository<SkyPackage, Long>{

    public SkyPackage findSkyPackageById(long id);

    public SkyPackage findSkyPackageByName(String name);

//    public SkyPackage findSkyPackage(SkyPackage skyPackage);

    public List<SkyPackage> findAll();

    public void delete(long id);
}
