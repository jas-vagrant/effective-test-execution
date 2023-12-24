package com.testvagrant.example.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InnerClass {

    private static final Logger LOG = LoggerFactory.getLogger(InnerClass.class);

    public static void firstInnerMethod() {
        LOG.info("Inside first inner method");
    }

    public static void secondInnerMethod() {
        LOG.info("Inside second inner method");
    }
}
