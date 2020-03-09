package com.eddi;

import com.eddi.service.SQLService;

public class MainApplication {
    public static void main(String[] args) {
        String inputFile = args[1];
        String outputFile = args[2];
        SQLService service = new SQLService();

        if(args[0].equals("search")) {
            service.searchQuery(inputFile, outputFile);

        }else if(args[0].equals("stat")) {
            service.statQuery(inputFile, outputFile);

        }else {
            service.exception(outputFile);
        }
    }
}
