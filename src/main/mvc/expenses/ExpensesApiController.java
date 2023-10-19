package com.nighthawk.spring_portfolio.mvc.expenses;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.calendar.Calendar;

@RestController
@RequestMapping("/expenses")
public class ExpensesApiController {

    @Autowired
    private ExpensesJpaRepository expensesRepository;

    @GetMapping("/")
    public ResponseEntity<List<Expenses>> getExpenses() {
        return new ResponseEntity<>(expensesRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    // @PostMapping("/updateAll") // ability to update one or all depending on how many boxes are filled
    // public ResponseEntity<Object> updateExpenses(@RequestBody final Map<String, Double> m) {
    //     double groceries = m.get("groceries");
    //     double transportation = m.get("transportation");
    //     double education = m.get("education");
    //     double housing = m.get("housing");
    //     double shopping = m.get("shopping");
    //     double utilities = m.get("utilities");
    //     double insurance = m.get("insurance");
    //     double personalExpenses = m.get("personalExpenses");
    //     double subscriptions = m.get("subscriptions");
    //     double investments = m.get("investments");
    //     double miscellaneous = m.get("miscellaneous");

    //     Expenses expenses = expensesRepository.findByGroceries(groceries);

    //     if (expenses != null) {
    //         expenses.setGroceries(groceries);
    //         expenses.setTransportation(transportation);
    //         expenses.setEducation(education);
    //         expenses.setHousing(housing);
    //         expenses.setShopping(shopping);
    //         expenses.setUtilities(utilities);
    //         expenses.setInsurance(insurance);
    //         expenses.setPersonalExpenses(personalExpenses);
    //         expenses.setSubscriptions(subscriptions);
    //         expenses.setInvestments(investments);
    //         expenses.setMiscellaneous(miscellaneous);

    //         Expenses total = expenses.calculateExpenses(groceries, transportation, education,
    //         housing, shopping, utilities, insurance, personalExpenses, subscriptions,
    //         investments, miscellaneous);
    //         expensesRepository.save(expenses);
    //         expensesRepository.save(total);

    //         return new ResponseEntity<>(expenses + " updated", HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
    // }

    // @PostMapping("/post") //new page for posting - rather than pop out window on mai dashboard
    // public ResponseEntity<Object> postGroceries(@RequestBody final Map<String, Double> m) { // <List<Expenses>>
    //     double groceries = m.get("groceries");
    //     double transportation = m.get("transportation");
    //     double education = m.get("education");
    //     double housing = m.get("housing");
    //     double shopping = m.get("shopping");
    //     double utilities = m.get("utilities");
    //     double insurance = m.get("insurance");
    //     double personalExpenses = m.get("personalExpenses");
    //     double subscriptions = m.get("subscriptions");
    //     double investments = m.get("investments");
    //     double miscellaneous = m.get("miscellaneous");
    //     Expenses post = new Expenses(groceries, transportation, education, housing, 
    //     shopping, utilities, insurance, personalExpenses, subscriptions, investments, miscellaneous);
    //     Expenses total = post.calculateExpenses(groceries, transportation, education, housing, shopping, utilities,
    //     insurance, personalExpenses, subscriptions, investments, miscellaneous);

    //     expensesRepository.save(post);
    //     expensesRepository.save(total);
    //     return new ResponseEntity<>(post + " listed successfully!", HttpStatus.CREATED);
    // }

    // this.groceries = groceries;
    // this.transportation = transportation;
    // this.education = education;
    // this.housing = housing;
    // this.shopping = shopping;
    // this.utilities = utilities;
    // this.insurance = insurance;
    // this.personal = personal;
    // this.subscriptions = subscriptions;
    // this.investments = investments;
    // this.miscellaneous = miscellaneous;

    @PostMapping("/create/{groceries}/{transportation}/{education}/{housing}/{shopping}/{utilities}/{insurance}/{personal}/{subscriptions}/{investments}/{miscellaneous}")
    public ResponseEntity<Expenses> createExpenses(@PathVariable(required=false) String groceries, @PathVariable(required=false) String transportation,
    @PathVariable(required=false) String education, @PathVariable(required=false) String housing, @PathVariable(required=false) String shopping, @PathVariable(required=false) String utilities,
    @PathVariable(required=false) String insurance, @PathVariable(required=false) String personal, @PathVariable(required=false) String subscription, @PathVariable(required=false) String investments,
    @PathVariable(required=false) String miscellaneous) {
        Expenses e = new Expenses(null, groceries, transportation, education, housing, shopping, utilities, 
        insurance, personal, subscription, investments, miscellaneous);
        // ExpensesRepository.saveAndFlush(new Expenses(shopping, eatingOut, travel, miscellaneous));
        expensesRepository.saveAndFlush(b);
        total = e.calculateExpenses(groceries, transportation, education, housing, shopping, utilities, 
        insurance, personal, subscription, investments, miscellaneous);
        expensesRepository.saveAndFlush(total);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expenses> updateListing(@PathVariable long id, @RequestBody Expenses changes) {
        Optional<Expenses> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Expenses a = optional.get(); // value from findByID
            a.setGroceries(changes.getGroceries()); // value from findByID
            a.setTransportation(changes.getTransportation()); // value from findByID
            a.setEducation(changes.getEducation()); // value from findByID
            a.setShopping(changes.getShopping()); // value from findByID
            a.setUtilities(changes.getUtilities()); // value from findByID
            a.setInsurance(changes.getInsurance()); // value from findByID
            a.setPersonal(changes.getPersonal()); // value from findByID
            a.setSubscriptions(changes.getSubscriptions()); // value from findByID
            a.setInvestments(changes.getInvestments()); // value from findByID
            a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
            repository.save(a);
            total = a.calculateExpenses(groceries, transportation, education, housing, shopping, utilities, 
        insurance, personal, subscription, investments, miscellaneous);
            repository.save(total);
            return new ResponseEntity<>(a, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
    }
}
