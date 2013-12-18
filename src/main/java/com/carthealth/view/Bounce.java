package main.java.com.carthealth.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bounce {
  
  private String[] IL_counties =
    {"Adams",
    "Alexander",
    "Bond",
    "Boone",
    "Brown",
    "Bureau",
    "Calhoun",
    "Carroll",
    "Cass",
    "Champaign",
    "Christian",
    "Clark",
    "Clay",
    "Clinton",
    "Coles",
    "Cook",
    "Crawford",
    "Cumberland",
    "DeKalb",
    "De Witt",
    "Douglas",
    "DuPage",
    "Edgar",
    "Edwards",
    "Effingham",
    "Fayette",
    "Ford",
    "Franklin",
    "Fulton",
    "Gallatin",
    "Greene",
    "Grundy",
    "Hamilton",
    "Hancock",
    "Hardin",
    "Henderson",
    "Henry",
    "Iroquois",
    "Jackson",
    "Jasper",
    "Jefferson",
    "Jersey",
    "Jo Daviess",
    "Johnson",
    "Kane",
    "Kankakee",
    "Kendall",
    "Knox",
    "Lake",
    "La Salle",
    "Lawrence",
    "Lee",
    "Livingston",
    "Logan",
    "McDonough",
    "McHenry",
    "McLean",
    "Macon",
    "Macoupin",
    "Madison",
    "Marion",
    "Marshall",
    "Mason",
    "Massac",
    "Menard",
    "Mercer",
    "Monroe",
    "Montgomery",
    "Morgan",
    "Moultrie",
    "Ogle",
    "Peoria",
    "Perry",
    "Piatt",
    "Pike",
    "Pope",
    "Pulaski",
    "Putnam",
    "Randolph",
    "Richland",
    "Rock Island",
    "St. Clair",
    "Saline",
    "Sangamon",
    "Schuyler",
    "Scott",
    "Shelby",
    "Stark",
    "Stephenson",
    "Tazewell",
    "Union",
    "Vermilion",
    "Wabash",
    "Warren",
    "Washington",
    "Wayne",
    "White",
    "Whiteside",
    "Will",
    "Williamson",
    "Winnebago",
    "Woodford"};
  
  @RequestMapping(value="/bounce/test.json", method=RequestMethod.GET, consumes="application/json")
  public String getStats(@RequestParam String token) {
    String bounce = "{\"msg\":\"Hi there JS!\",";
    bounce = bounce + "\"" + token + "\": null,";
    bounce = bounce + "\"end\":null}";
    return bounce;
  }
  
  @RequestMapping("*")
  public String hello(HttpServletRequest request) {
      return request.getServletPath();
  }
}  