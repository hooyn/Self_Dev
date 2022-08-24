package org.serial;

public class Main {
    public static void main(String[] args) {
        try{
            (new Serial()).connect("COM1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
