package in.harininotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import in.harininotes.util.LoggingUtil;
import in.harininotes.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class HealthCheckController{

    private final ProductController productController;

    //Constructor-based dependency injection 
    @Autowired
    public HealthCheckController(ProductController productController){
        this.productController = productController;
    }

    @GetMapping("/health/readiness")
    public String readinessProbe(){
        long start= System.currentTimeMillis();
        try{
            boolean isReady = checkReadiness();
            long end = System.currentTimeMillis();
            String status = isReady ? "SUCCESS":"FAILURE";
            LoggingUtil.logProbe("Readiness",status,end-start);
            return isReady? "READY":"NOT READY";
        }
        catch(Exception e){
            long end = System.currentTimeMillis();
            LoggingUtil.logProbe("Readiness", "FAILURE", end-start);
            return "NOT READY";
        }
       
    }

    @GetMapping("/health/liveness")
    public String livenessProbe(){
        long start = System.currentTimeMillis();
        try{
            boolean isAlive = checkLiveness();
            long end = System.currentTimeMillis();
            String status = isAlive ? "SUCCESS" : "FAILURE";
            LoggingUtil.logProbe("Liveness", status, end-start);
            return isAlive? "ALIVE":"NOT ALIVE";
        }
        catch(Exception e){
            long end = System.currentTimeMillis();
            LoggingUtil.logProbe("Liveness", "FAILURE", end-start);
            return "NOT ALIVE";
        }
       
    }

    @GetMapping("/health/startup")
    public String startupProbe(){
        long start = System.currentTimeMillis();
        try{
            boolean isStarted = checkStartup();
            long end = System.currentTimeMillis();
            String status = isStarted ? "SUCCESS" : "FAILURE";
            LoggingUtil.logProbe("Startup", status, end-start);
            return isStarted ? "STARTED":"NOT STARTED";
        }
        catch(Exception e){
            long end = System.currentTimeMillis();
            LoggingUtil.logProbe("Startup", "FAILURE", end-start);
            return "NOT STARTED";
        }
        
    }
    //checking readiness probes - if the products are available and map is initialized properly
    public boolean checkReadiness(){
        
        return true;
    }

    //Liveness probe: 
    public boolean checkLiveness(){
        
        return true;
    }

    public boolean checkStartup() {
        
        return true;
    }
}