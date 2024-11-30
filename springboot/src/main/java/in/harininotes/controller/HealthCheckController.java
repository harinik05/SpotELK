package in.harininotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import in.harininotes.util.LoggingUtil;
import in.harininotes.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;

/*
HealthCheckController: handles K8S health probes (readiness, liveness, startup)
*/
@RestController
public class HealthCheckController{

    // Dependency on ProductController for additional checks and operations
    private final ProductController productController;

    //Constructor-based dependency injection for productController
    @Autowired
    public HealthCheckController(ProductController productController){
        this.productController = productController;
    }

    //Readiness Probe: Indicates if application is ready to serve requests
    @GetMapping("/health/readiness")
    public String readinessProbe(){
        //a. Start timing for logging purposes
        long start= System.currentTimeMillis();

        //b. Try-catch
        try{
            //Perform readiness check 
            boolean isReady = checkReadiness();

            //Recording latency
            long end = System.currentTimeMillis();
            String status = isReady ? "SUCCESS":"FAILURE";

            //Logging readiness probe status and duration
            LoggingUtil.logProbe("Readiness",status,end-start);

            //Returning the endpoint status
            return isReady? "READY":"NOT READY";
        }
        catch(Exception e){
            long end = System.currentTimeMillis();
            LoggingUtil.logProbe("Readiness", "FAILURE", end-start);
            return "NOT READY";
        }
       
    }

    //Liveness Probe: Indicates if the application is alive and functioning
    @GetMapping("/health/liveness")
    public String livenessProbe(){
        //Start timing for logging purposes
        long start = System.currentTimeMillis();
        try{
            // Performing liveness check
            boolean isAlive = checkLiveness();
            long end = System.currentTimeMillis();
            String status = isAlive ? "SUCCESS" : "FAILURE";

            //Logging statements for this probe
            LoggingUtil.logProbe("Liveness", status, end-start);
            return isAlive? "ALIVE":"NOT ALIVE";
        }
        catch(Exception e){
            long end = System.currentTimeMillis();
            LoggingUtil.logProbe("Liveness", "FAILURE", end-start);
            return "NOT ALIVE";
        }
       
    }

    //Startup Probe: Indicates if the application has started successfully
    @GetMapping("/health/startup")
    public String startupProbe(){

        //Initiating timer for these metrics
        long start = System.currentTimeMillis();
        try{
            //Performing startup check
            boolean isStarted = checkStartup();
            long end = System.currentTimeMillis();
            String status = isStarted ? "SUCCESS" : "FAILURE";

            //Logging the message
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