package com.eddi;

import com.eddi.service.SQLService;

public class MainApplication {
    public static void main(String[] args) {
        /*String type = args[2];
        String inputFile = args[3];
        String outputFile = args[4];*/
        String type = "search";
        SQLService service = new SQLService();

        if(type == "search") {
            service.searchQuery();
        }else if(type == "stat") {
            service.statQuery();
        }else {
            service.exception();
        }
    }
}
