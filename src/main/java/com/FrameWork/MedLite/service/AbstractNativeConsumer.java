/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.service;

/**
 *
 * @author Administrator
 */
import java.util.concurrent.atomic.AtomicBoolean; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  abstract class AbstractNativeConsumer {
     protected final Logger log = LoggerFactory.getLogger(AbstractNativeConsumer.class);
    
    protected final AtomicBoolean closed = new AtomicBoolean(false);
 
    abstract public void start();
 
    public Boolean getConsumerStatus() {
        return Boolean.FALSE;
    }
    
    public String getConsumerIdentity() {
        return "UNIDENTIFIED";
    } 

}
