package src.main.java.skypackage.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface PackageDAO extends JpaRepository<Package,Long>{

    public Package findPackageById(long id);

    public Package getPackageByPackage(Package package);

    public List<Package> findAll();

    public void delete(long id);
}
