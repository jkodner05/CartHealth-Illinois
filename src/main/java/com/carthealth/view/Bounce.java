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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

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
  
<<<<<<< HEAD
  @RequestMapping(value="/bounce/test.req", method=RequestMethod.GET, consumes="application/json")
  @ResponseBody
  public String getStats(@RequestParam String token) {
=======
  @RequestMapping(value="/")
  public ModelAndView homePage() {           
      return new ModelAndView("redirect:/map_test.html");
  }

  @Autowired
  @RequestMapping(value="/bounce.html", method=RequestMethod.GET, consumes="application/json")
  public @ResponseBody String getStats(@RequestParam String token) {
>>>>>>> 60d7fd4999899acadfcff77771a0047b2c78d995
    String bounce = "{\"msg\":\"Hi there JS!\",";
    bounce = bounce + "\"" + token + "\": null,";
    bounce = bounce + "\"end\":null}";
    return bounce;
  }
<<<<<<< HEAD
}  
=======
}  
>>>>>>> 60d7fd4999899acadfcff77771a0047b2c78d995
