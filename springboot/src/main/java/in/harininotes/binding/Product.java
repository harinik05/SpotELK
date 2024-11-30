package in.harininotes.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
	
	//Product ID: Optional field, this can be null
	private Integer pid;

	//Product Name: Specifying that the activity must be between 3-15 characters in length
	@NotNull(message = "Name is required")
	@Size(min = 3, max = 15, message = "Name should have min 3 max 15 characters")
	private String pname;

	//Price: Shows the priority behind the task that'll be worked on
	@NotNull(message = "Price is required")
	private Double price;

	//Quantity: Time that will be required to get the task completed
	@NotNull(message = "Quantity is required")
	private Integer qty;

	//Getter method: Retrieving the PID 
	public Integer getPid() {
		return pid;
	}

	//Setter method: Updating PID
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	//Getter Method: Retrieving task name
	public String getPname() {
		return pname;
	}

	//Setter method: Updating task name
	public void setPname(String pname) {
		this.pname = pname;
	}

	//Getter Method: retrieving priority
	public Double getPrice() {
		return price;
	}
	
	//Setter method: Updating the priority
	public void setPrice(Double price) {
		this.price = price;
	}

	//Getter Method: Retrieving timeline
	public Integer getQty() {
		return qty;
	}

	//Setter Method: Updating the timeline value
	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
