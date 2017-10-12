package skypackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkyPackageService {
    @Autowired
    private SkyPackageDAO skyPackageDAO;

    public SkyPackage findSkyPackageById(long id) {
        return skyPackageDAO.findSkyPackageById(id);
    }

    public SkyPackage findSkyPackageByName(String name) {
        return skyPackageDAO.findSkyPackageByName(name);
    }

    public boolean isSkyPackageExisting(long id) {
        return skyPackageDAO.exists(id);
    }

//    public SkyPackage findSkyPackage(SkyPackage skyPackage) {
//        return skyPackageDAO.findSkyPackage(skyPackage);
//    }

    public List<SkyPackage> findAllSKyPackages() {
        return skyPackageDAO.findAll();
    }

    public boolean removeSkyPackage(long id) {
        skyPackageDAO.delete(id);
        return true;
    }

    public boolean removeAllSkyPackages() {
         skyPackageDAO.deleteAll();
         return true;
    }

    public SkyPackage createSkyPackage(SkyPackage skyPackage) {
        return skyPackageDAO.save(skyPackage);
    }

    public SkyPackage updateSkyPackage(SkyPackage skyPackage) {
        SkyPackage currentSkyPackage = findSkyPackageById(skyPackage.getId());
        currentSkyPackage.setPrice(skyPackage.getPrice());
        currentSkyPackage.setName(skyPackage.getName());

        return skyPackageDAO.save(currentSkyPackage);
    }
}
