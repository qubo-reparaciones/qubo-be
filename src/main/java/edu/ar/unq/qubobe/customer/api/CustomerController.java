package edu.ar.unq.qubobe.customer.api;

import edu.ar.unq.qubobe.customer.Customer;
import edu.ar.unq.qubobe.customer.CustomerRegistrar;
import edu.ar.unq.qubobe.customer.api.to.CustomerTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CustomerController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    public static final String basePath = "/customer";
    public static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerRegistrar customerRegistrar;

    @Autowired
    public CustomerController(CustomerRegistrar customerRegistrar) {
        this.customerRegistrar = customerRegistrar;
    }

    @PostMapping
    public List<Customer> register(@RequestBody CustomerTO customerTO) {
        Customer customer = customerTO.get();
        customerRegistrar.register(customer);
        logger.info("todo bien por acá: " + customer.toString());
        return customerRegistrar.getAll();
    }
}

