package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	public String viewPilot(String licenseNumber, Model model) {
		PilotModel temp = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(temp == null) {
			return "error-licenseNumber";
		}
		List<FlightModel> listFlights = temp.getPilotFlight();
		model.addAttribute("pilot", temp);
		model.addAttribute("flight", listFlights);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete", method = RequestMethod.GET)
	public String deletePilot(String licenseNumber, Model model) {
		PilotModel temp = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(temp == null) {
			return "error-licenseNumber";
		} else {
			pilotService.deletePilot(temp);
			return "delete";
		}
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.GET)
	public String updatePilot(String licenseNumber, Model model) {
		PilotModel temp = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(temp == null) {
			return "error-licenseNumber";
		} else {
			//pilotService.updatePilot(temp);
			return "update";
		}
	}
}
