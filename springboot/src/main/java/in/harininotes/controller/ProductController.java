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

@Controller
public class ProductController {

	private Map<Integer, Product> map = new HashMap<>();
	
	int pid = 1;

	// method to display form

	public Map<Integer, Product> getProductMap(){
		return map;
	}

	public int getPid(){
		return pid;
	}
	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		LoggingUtil.logInfo("Product view page loaded bello...");
		model.addAttribute("products", map.values());
		return "data";
	}

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
	
	@GetMapping("/product")
	public String delete(@RequestParam("pid") Integer pid, Model model) {
		map.remove(pid);
		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("products", map.values());
		LoggingUtil.logInfo("Product deleted...");
		return "data";
	}
}
