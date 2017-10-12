package skypackage.services;

import java.util.List;

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

@RestController
@RequestMapping(value="skypackage")
public class SkyPackageController {

    @Autowired
    SkyPackageService skyPackageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SkyPackage getskyPackage(@PathVariable("id") int id) {
        return skyPackageService.findSkyPackageById(id);
    }

    //-------------------Retrieve All skyPackages--------------------------------------------------------
    @RequestMapping(value = "/skypackage/", method = RequestMethod.GET)
    public ResponseEntity<SkyPackages> listAllSkyPackage() {
        List<SkyPackage> packages = skyPackageService.findAllSKyPackages();
        SkyPackages skyPackages = new SkyPackages(packages);
        if(packages.isEmpty()){
            return new ResponseEntity<SkyPackages>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<SkyPackages>(skyPackages, HttpStatus.OK);
    }

    //-------------------Retrieve Single SkyPackage--------------------------------------------------------
    @RequestMapping(value = "/skypackage/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkyPackage> getskyPackage(@PathVariable("id") long id) {
        System.out.println("Fetching SkyPackage with id " + id);
        SkyPackage skyPackage = skyPackageService.findSkyPackageById(id);
        if (skyPackage == null) {
            System.out.println("SkyPackage with id " + id + " not found");
            return new ResponseEntity<SkyPackage>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SkyPackage>(skyPackage, HttpStatus.OK);
    }

    //-------------------Retrieve Single SkyPackage--------------------------------------------------------
    @RequestMapping(value = "/skypackage/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkyPackage> getskyPackage(@PathVariable("name") String name) {
        System.out.println("Fetching SkyPackage with name " + name);
        SkyPackage skyPackage = skyPackageService.findSkyPackageByName(name);
        if (skyPackage == null) {
            System.out.println("SkyPackage with name " + name + " not found");
            return new ResponseEntity<SkyPackage>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SkyPackage>(skyPackage, HttpStatus.OK);
    }

    //-------------------Create a SkyPackage--------------------------------------------------------
    @RequestMapping(value = "/skypackage/", method = RequestMethod.POST)
    public ResponseEntity<Void> createskyPackage(@RequestBody SkyPackage skyPackage, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating SkyPackage " + skyPackage.getId());

        if (skyPackageService.isSkyPackageExisting(skyPackage.getId())) {
            System.out.println("A SkyPackage with id " + skyPackage.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        skyPackageService.createSkyPackage(skyPackage);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/skypackage/{id}").buildAndExpand(skyPackage.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a SkyPackage --------------------------------------------------------
    @RequestMapping(value = "/skypackage/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SkyPackage> updateskyPackage(@PathVariable("id") long id, @RequestBody SkyPackage skyPackage) {
        System.out.println("Updating SkyPackage " + id);

        if (skyPackage ==null) {
            System.out.println("SkyPackage with id " + id + " not found");
            return new ResponseEntity<SkyPackage>(HttpStatus.NOT_FOUND);
        }

        skyPackage = skyPackageService.updateSkyPackage(skyPackage);
        return new ResponseEntity<SkyPackage>(skyPackage, HttpStatus.OK);
    }

    //------------------- Delete a SkyPackage --------------------------------------------------------
    @RequestMapping(value = "/skyPackage/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SkyPackage> deleteskyPackage(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting SkyPackage with id " + id);

        SkyPackage skyPackage = skyPackageService.findSkyPackageById(id);
        if (skyPackage == null) {
            System.out.println("Unable to delete. SkyPackage with id " + id + " not found");
            return new ResponseEntity<SkyPackage>(HttpStatus.NOT_FOUND);
        }

        boolean removed = skyPackageService.removeSkyPackage(id);
        return new ResponseEntity<SkyPackage>(HttpStatus.NO_CONTENT);
    }

    //------------------- Delete All SkyPackage --------------------------------------------------------
    @RequestMapping(value = "/skyPackage/", method = RequestMethod.DELETE)
    public ResponseEntity<SkyPackage> deleteAllSkyPackages() {
        System.out.println("Deleting All skyPackages");

        boolean removed = skyPackageService.removeAllSkyPackages();
        return new ResponseEntity<SkyPackage>(HttpStatus.NO_CONTENT);
    }

}