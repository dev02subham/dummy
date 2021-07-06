package com.cts.portal.controller;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.portal.exception.IPTreatmentPackageNotFoundException;
import com.cts.portal.feign.IPTreatmentOfferingClient;
import com.cts.portal.model.FormInputsGetByPackageName;
import com.cts.portal.model.FormsInputsGetByExpertise;
import com.cts.portal.model.IPTreatmentPackage;
import com.cts.portal.model.PackageDetail;
import com.cts.portal.model.SpecialistDetail;

@Controller
@RequestMapping("/portal")
public class IpTreatmentOfferingController {

	@Autowired
	private IPTreatmentOfferingClient client;

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/specialists")
	public ModelAndView showSpecialistPage(HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		/*
		 * get the list of specialists using feign client of IPOfferingMicroservice
		 */
		System.out.println("Inside /specialists");
		List<SpecialistDetail> specialists = client
				.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("user-view-list-of-specialist-page");
		model.addObject("specialists", specialists);
		return model;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/ipTreatmentPackages")
	public ModelAndView showIPTreatmentPackages(Model model, HttpServletRequest request) throws Exception {
		System.out.println("Inside IP Treatment Packages");
		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		List<IPTreatmentPackage> packageDetails = client
				.getAllIPTreatmentPackage((String) request.getSession().getAttribute("Authorization"));
		ModelAndView modelAndView = new ModelAndView("admin-view-package-update");
		modelAndView.addObject("ipTreatmentPackagekageName", packageDetails);
		return modelAndView;
	}

	/**
	 * @param formInputsGetByPackageName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/ipTreatmentPackageByName")
	public ModelAndView showIPTreatmentPackageByName2(
			@ModelAttribute("formInputsGetByPackageName") FormInputsGetByPackageName formInputsGetByPackageName,
			HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		/*
		 * if token is set, 
		 * then allow access to view
		 */
		ModelAndView model = new ModelAndView("user-package-detail-by-name-page");
		if (formInputsGetByPackageName != null && formInputsGetByPackageName.getAilment() != null
				&& formInputsGetByPackageName.getPackageName() != null) {
			try {
				/*
				 * get the package details by Name 
				 * using feign client of IPOfferingMicroservice
				 */
				IPTreatmentPackage ipTreatmentPackagekageName = client.getIPTreatmentPackageByName(
						formInputsGetByPackageName.getAilment(),
						formInputsGetByPackageName.getPackageName(),
						(String) request.getSession().getAttribute("Authorization"));
				
				model.addObject("ipTreatmentPackagekageName", ipTreatmentPackagekageName);
			} catch (IPTreatmentPackageNotFoundException e) {
				model.addObject("error", e.getMessage());
			}
		}
		return model;
	}
	
	
	@GetMapping(value = "/ipTreatmentspecialist")
	public ModelAndView showIPTreatmentspecialistsByExpertise(
			@ModelAttribute("formInputsGetByExpertise") FormsInputsGetByExpertise formsInputsGetByExpertise,
			HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		ModelAndView model = new ModelAndView("user-specialist-search-by-specialization");
		if (formsInputsGetByExpertise != null && formsInputsGetByExpertise.getAilment() != null) {
			List<SpecialistDetail> specialist = client.getIPSpecialistByExpertise(
						formsInputsGetByExpertise.getAilment(),
						(String) request.getSession().getAttribute("Authorization"));
				model.addObject("specialists", specialist);	
			
		}
		return model;
	}
	
	@GetMapping(value="/addSpeclialistForm")
	public ModelAndView addSpeclialistForm(@ModelAttribute("specialistDetail") SpecialistDetail specialistDetail,HttpServletRequest request) throws Exception {
		
		System.out.println("Inside /addSpeclialistForm");
		List<SpecialistDetail> specialists = client
				.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("add-specialist-form-page");
		return model;
		
	}
	
	@GetMapping(value = "/adminSpecialists")
	public ModelAndView showAdminSpecialist(HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		System.out.println("Inside /adminSpecialists");
		List<SpecialistDetail> specialists = client
				.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("admin-view-specialist-page");
		model.addObject("specialists", specialists);
		return model;
		
	}
	
	
	@PostMapping(value = "/addSpecialist")
	public ModelAndView addSpecialist(
			@ModelAttribute("specialistDetail") SpecialistDetail specialistDetail,
			HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		System.out.println("Inside /addSpecialists");
		
			SpecialistDetail specialist = client.addSpecialist(specialistDetail,
						(String) request.getSession().getAttribute("Authorization"));
			List<SpecialistDetail> sd = client
					.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
			ModelAndView model = new ModelAndView("admin-view-specialist-page");
				model.addObject("specialists", sd);	
			
		
		return model;
	}
	
	@GetMapping(value="/deleteSpe")
	public ModelAndView deleteSpe(@ModelAttribute("specialistDetail") SpecialistDetail specialistDetail,HttpServletRequest request) throws Exception {
		System.out.println("Inside delete specialist");
		List<SpecialistDetail> specialists = client
				.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("delete-specialist");
		model.addObject("specialists", specialists);	
		return model;
	}
	
	@GetMapping(value = "/deleteSpecialist")
	public ModelAndView deleteSpecialist(
			@RequestParam(value="specialistId",required = false) int id,
			HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		if(id>0) {
		client.deleteSpecialist(id,(String) request.getSession().getAttribute("Authorization"));
		}
		List<SpecialistDetail> specialist = client
				.getAllSpecialist((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("admin-view-specialist-page");
		model.addObject("specialists", specialist);
		return model;
	}
	
	
	@GetMapping(value = "/adminIpTreatmentPackages")
	public ModelAndView showAdminUpdatePackages(Model model, HttpServletRequest request) throws Exception {
		System.out.println("Inside IP Treatment Packages");
		if ((String) request.getSession().getAttribute("Authorization") == null) {

			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		List<IPTreatmentPackage> packageDetails = client
				.getAllIPTreatmentPackage((String) request.getSession().getAttribute("Authorization"));
		ModelAndView modelAndView = new ModelAndView("admin-view-package-update");
		modelAndView.addObject("ipTreatmentPackagekageName", packageDetails);
		return modelAndView;
	}
	
	
	
	@GetMapping(value="/updatePackage")
	public ModelAndView updatePackage(@ModelAttribute("packageDetail") PackageDetail packageDetail) {
		System.out.println("Inside update Treatment Packages");
		ModelAndView model = new ModelAndView("update-view-package-page");
		
		return model;
	}
	
	
	@GetMapping(value = "/updateTreatmentPackage")
	public ModelAndView updateTreatmentPackage(
			@RequestParam(value = "pid",required =false) int id,
			@ModelAttribute("packageDetail") PackageDetail packageDetail,
			HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView login = new ModelAndView("error-page401");
			return login;
		}
		
		if(packageDetail!=null && id>0) {
		
		client.updateTreatmentPackage(id, packageDetail,
						(String) request.getSession().getAttribute("Authorization"));				
		}
		List<IPTreatmentPackage> packageDetails = client
				.getAllIPTreatmentPackage((String) request.getSession().getAttribute("Authorization"));
		ModelAndView modelAndView = new ModelAndView("admin-view-package-update");
		modelAndView.addObject("ipTreatmentPackagekageName", packageDetails);
		return modelAndView;
		
	}
	
	

	@ModelAttribute("ailmentList")
	public Set<String> populateAilmentEnumList() {
		return EnumSet.allOf(com.cts.portal.model.AilmentCategory.class).stream().map(a -> a.name())
				.collect(Collectors.toSet());

	}
	
	@ModelAttribute("areaOfExpertise")
	public List<String> populateAreaOfExpertise() {
		return Arrays.asList("UROLOGY", "ORTHOPAIDICS");

	}

	@ModelAttribute("packageList")
	public List<String> populatePackageList() {
		return Arrays.asList("Package 1", "Package 2");

	}
	@ModelAttribute("testDetails")
	public List<String> populateTestDetails() {
		return Arrays.asList("OPT1,OPT2", "UPT1,UPT2", "UPT3,UPT4", "OPT3, OPT4");

	}
}
