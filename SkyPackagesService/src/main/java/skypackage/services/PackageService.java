package src.main.java.skypackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageDAO packageDAO;

    public Package findPackageById(long id) {
        return packageDAO.findPackageById(id);
    }

    public boolean isPackageExisting(long id) {
        return packageDAO.exists(id);
    }

    public Package findPackageByPackage(Package aPackage) {
        return packageDAO.getPackageByReservervation(aPackage);
    }

    public List<Package> findAllPackages() {
        return packageDAO.findAll();
    }

    public boolean removePackage(long id) {
        packageDAO.delete(id);
        return true;
    }

    public boolean removeAllPackages() {
         packageDAO.deleteAll();
         return true;
    }

    public Package createPackage(Package aPackage) {
        return packageDAO.save(aPackage);
    }

    public Package updatePackage(Package aPackage) {
        Package currentPackage = findPackageById(aPackage.getId());
        currentPackage.setPrice(aPackage.getPrice());
        currentPackage.setName(aPackage.getName());

        return packageDAO.save(currentPackage);
    }
}
