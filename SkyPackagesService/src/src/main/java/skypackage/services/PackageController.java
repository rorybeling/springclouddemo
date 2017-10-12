package src.main.java.skypackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="package")
public class PackageController {

    @Autowired
    PackageService packageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Package getPackage(@PathVariable("id") int id) {
        return packageService.findPackageById(id);
    }

    //-------------------Retrieve All Packages--------------------------------------------------------
    @RequestMapping(value = "/package/", method = RequestMethod.GET)
    public ResponseEntity<List<Package>> listAllPackage() {
        List<Package> aPackages = packageService.findAllPackages();
        if(aPackages.isEmpty()){
            return new ResponseEntity<List<Package>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Package>>(aPackages, HttpStatus.OK);
    }

    //-------------------Retrieve Single Package--------------------------------------------------------
    @RequestMapping(value = "/package/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Package> getPackage(@PathVariable("id") long id) {
        System.out.println("Fetching Package with id " + id);
        Package aPackage = packageService.findPackageById(id);
        if (aPackage == null) {
            System.out.println("Package with id " + id + " not found");
            return new ResponseEntity<Package>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Package>(aPackage, HttpStatus.OK);
    }

    //-------------------Create a Package--------------------------------------------------------
    @RequestMapping(value = "/aPackage/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPackage(@RequestBody Package aPackage, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Package " + aPackage.getId());

        if (packageService.isPackageExisting(aPackage.getId())) {
            System.out.println("A Package with id " + aPackage.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        packageService.createPackage(aPackage);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/aPackage/{id}").buildAndExpand(aPackage.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Package --------------------------------------------------------
    @RequestMapping(value = "/aPackage/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Package> updatePackage(@PathVariable("id") long id, @RequestBody Package aPackage) {
        System.out.println("Updating Package " + id);

        if (aPackage ==null) {
            System.out.println("Package with id " + id + " not found");
            return new ResponseEntity<Package>(HttpStatus.NOT_FOUND);
        }

        aPackage = packageService.updatePackage(aPackage);
        return new ResponseEntity<Package>(aPackage, HttpStatus.OK);
    }

    //------------------- Delete a Package --------------------------------------------------------
    @RequestMapping(value = "/resrvation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Package> deletePackage(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Package with id " + id);

        Package aPackage = packageService.findPackageById(id);
        if (aPackage == null) {
            System.out.println("Unable to delete. Package with id " + id + " not found");
            return new ResponseEntity<Package>(HttpStatus.NOT_FOUND);
        }

        boolean removed = packageService.removePackage(id);
        return new ResponseEntity<Package>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All Package --------------------------------------------------------
    @RequestMapping(value = "/resrvation/", method = RequestMethod.DELETE)
    public ResponseEntity<Package> deleteAllPackages() {
        System.out.println("Deleting All Packages");

        boolean removed = packageService.removeAllPackages();
        return new ResponseEntity<Package>(HttpStatus.NO_CONTENT);
    }

}