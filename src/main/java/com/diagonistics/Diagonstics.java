package com.diagonistics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import com.google.gson.Gson;

public class Diagonstics {
	
	public static String jsonDiagnosticsStats () {
        // Get the memory bean
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        // Get the heap memory usage
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        // Get the non-heap memory usage
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

        // Create a class to hold memory usage information
        MemoryInfo memoryInfo = new MemoryInfo(
                formatMemoryUsage(heapMemoryUsage),
                formatMemoryUsage(nonHeapMemoryUsage)
        );

        // Convert the memoryInfo object to JSON using Gson
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(memoryInfo);

        // Print the JSON output
        return jsonOutput;
    }

    // Helper method to format MemoryUsage object
    private static String formatMemoryUsage(MemoryUsage memoryUsage) {
        return String.format(
                "Init: %,d KB, Used: %,d KB, Committed: %,d KB, Max: %,d KB",
                memoryUsage.getInit() / 1024,
                memoryUsage.getUsed() / 1024,
                memoryUsage.getCommitted() / 1024,
                memoryUsage.getMax() / 1024
        );
    }

    // Class to hold memory usage information
    static class MemoryInfo {
        String heapMemoryUsage;
        String nonHeapMemoryUsage;

        MemoryInfo(String heapMemoryUsage, String nonHeapMemoryUsage) {
            this.heapMemoryUsage = heapMemoryUsage;
            this.nonHeapMemoryUsage = nonHeapMemoryUsage;
        }
    }
	
	
	
	
	
	
	
	
	
	

}
