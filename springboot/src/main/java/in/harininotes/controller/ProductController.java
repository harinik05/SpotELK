package in.harininotes.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.harininotes.binding.Product;
import in.harininotes.util.LoggingUtil;

/*
ProductController manages product-related operations like displaying form, 
saving, viewing and deleting items
*/
@Controller
public class ProductController {

	//Stores the results of product/activity in memory app
	private Map<Integer, Product> map = new HashMap<>();
	
	//Initialies product id to 1, and increments each time something is added
	int pid = 1;

	// Utility to return the product map (unnecessary as Product module contains this)
	public Map<Integer, Product> getProductMap(){
		return map;
	}

	//Utility to return pid (same as above)
	public int getPid(){
		return pid;
	}

	//Displays the product input form, Adds an empty product inst
	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("product", new Product());
		return "index"; //redering index.html
	}
	
	//Displays the list of all products
	@GetMapping("/products")
	public String getProducts(Model model) {
		LoggingUtil.logInfo("Product view page loaded bello...");
		model.addAttribute("products", map.values());
		return "data";
	}
	
	//Handles submission of form once the button is clicked on
	@PostMapping("/product")
	public String handleSubmitBtn(@Valid Product p, BindingResult result, Model model) {
		if (result.hasErrors()) {
			LoggingUtil.logWarn("Product form validations failed...");
			return "index";
		} else {
			LoggingUtil.logInfo("Product saved...");
			p.setPid(pid);
			map.put(pid, p);
			model.addAttribute("msg", "Product Saved");
			pid ++;
		}
		return "index";
	}
	
	//Delete the product 
	@GetMapping("/product")
	public String delete(@RequestParam("pid") Integer pid, Model model) {
		map.remove(pid);
		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("products", map.values());
		LoggingUtil.logInfo("Product deleted...");
		return "data";
	}
}
