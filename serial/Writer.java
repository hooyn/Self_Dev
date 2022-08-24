package org.serial;

import java.io.IOException;
import java.io.OutputStream;

public class Writer implements Runnable{

    OutputStream out;

    public Writer(OutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try{
            int c = 0;
            while((c = System.in.read()) > -1){
                this.out.write(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
