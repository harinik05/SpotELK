package in.harininotes.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;

public class LoggingUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    // Basic logging statements
    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarn(String message) {
        logger.warn(message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void logError(String message, Throwable throwable, long latencyMs) {
        logger.error("{} | Latency: {} ms", message, latencyMs, throwable);
    }

    // Measuring the latency
    public static void measureLatency(String taskDescription, Runnable task) {
        long start = Instant.now().toEpochMilli();

        try {
            task.run();
            long end = Instant.now().toEpochMilli();
            logInfo(taskDescription + " completed in " + (end - start) + " ms.");
        } catch (Exception e) {
            long end = Instant.now().toEpochMilli();
            logError(taskDescription + " failed.", e, end - start);
        }
    }

    // Log probe events (readiness, liveness, startup)
    public static void logProbe(String probeType, String status, long latencyMs) {
        String podName = System.getenv("POD_NAME");
        String podNamespace = System.getenv("POD_NAMESPACE");

        logger.info("ProbeType: {} | Status: {} | Latency: {} ms | PodName: {} | Namespace: {}",
                probeType,
                status,
                latencyMs,
                podName != null ? podName : "Unknown",
                podNamespace != null ? podNamespace : "Unknown");
    }
}
