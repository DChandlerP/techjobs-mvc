package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        // adds radial buttons!
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    //Got this straight from the tutorials
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){

        // 1st condition is if nothing is entered, second is if all is selected.
        if (searchType.equals("") || searchType.equals("all")){
            ArrayList<HashMap<String, String>> jobList;
            jobList = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobList", jobList);
            return "search";
        }
        //HashMap populates different based on params given
        else{
            ArrayList<HashMap<String, String>> jobList;
            jobList = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobList", jobList);
            return "search";
        }

    }

}
