package com.dodson.petclinic.bootStrap;

import java.time.LocalDate;

import com.dodson.petclinic.model.Owner;
import com.dodson.petclinic.model.Pet;
import com.dodson.petclinic.model.PetType;
import com.dodson.petclinic.model.Specialty;
import com.dodson.petclinic.model.Vet;
import com.dodson.petclinic.model.Visit;
import com.dodson.petclinic.services.OwnerService;
import com.dodson.petclinic.services.PetTypeService;
import com.dodson.petclinic.services.SpecialtyService;
import com.dodson.petclinic.services.VetService;
import com.dodson.petclinic.services.VisitService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

private void loadData() {
    PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogSavedPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType catSavedPetType = petTypeService.save(cat);

        System.out.println("Loaded Pet Types.............");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        System.out.println("Loaded Specialties.............");

        /** Owner 1 and pets */
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("12 Some St");
        owner1.setCity("Big City");
        owner1.setTelephone("123-123-1234");

        Pet owner1Pet1 = new Pet();
        owner1Pet1.setName("Rosco");
        owner1Pet1.setOwner(owner1);
        owner1Pet1.setPetType(dogSavedPetType);
        owner1Pet1.setBirthDate(LocalDate.now());
        owner1.getPets().add(owner1Pet1);

        ownerService.save(owner1);

        /** Owner 2 and pets */
        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Another St");
        owner2.setCity("Small Town");
        owner2.setTelephone("321-321-4321");

        Pet fionnaCat = new Pet();
        fionnaCat.setName("Pussy Cat");
        fionnaCat.setOwner(owner2);
        fionnaCat.setPetType(catSavedPetType);
        fionnaCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionnaCat);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Tom");
        owner3.setLastName("WestLike");
        owner3.setAddress("121 Another St");
        owner3.setCity("Kinda Big City");
        owner3.setTelephone("321-123-1234");

        Pet owner3Pet1 = new Pet();
        owner3Pet1.setName("Posco");
        owner3Pet1.setOwner(owner3);
        owner3Pet1.setPetType(dogSavedPetType);
        owner3Pet1.setBirthDate(LocalDate.now());
        owner3.getPets().add(owner3Pet1);

        ownerService.save(owner3);

        Visit catVisit = new Visit();
        catVisit.setPet(fionnaCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Cat has fleas");
        visitService.save(catVisit);

        System.out.println("Loaded Owners.............");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded Vets.............");
    }
}