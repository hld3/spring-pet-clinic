package com.dodson.petclinic.controllers;

import java.util.List;

import com.dodson.petclinic.model.Owner;
import com.dodson.petclinic.services.OwnerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        // allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		String lastName = owner.getLastName();
		List<Owner> ownersResults = ownerService.findAllByLastNameLike("%" + lastName +"%");
		if (ownersResults.isEmpty()) {
			// no owners found
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (ownersResults.size() == 1) {
			// 1 owner found
			owner = ownersResults.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}
		else {
			// multiple owners found
			model.addAttribute("listOwners", ownersResults);
			return "owners/ownersList";
		}
    }

    @GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = this.ownerService.findById(ownerId);
		mav.addObject(owner);
		return mav;
	}

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Validated Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			var savedOwner = ownerService.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}

	@GetMapping("/{id}/edit")
	public String initUpdateOwnerForm(Model model, @PathVariable Long id) {
		var foundOwner = ownerService.findById(id);

		if (foundOwner != null) {
			model.addAttribute("owner", foundOwner);
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			model.addAttribute("owner", Owner.builder().build());
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		
	}

	@PostMapping("/{id}/edit")
	public String processUpdateOwnerFormTest(@Validated Owner owner, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
			owner.setId(id);
			ownerService.save(owner);
			return "redirect:/owners/" + id;
		}
	}
}
