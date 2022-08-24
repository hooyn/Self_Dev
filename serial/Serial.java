package org.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class Serial {

    public Serial() {
    }

    void connect(String port) throws Exception {

        // CommPortIdentifier 포트가 실제로 존재하는지, 사용할 수 있는 상태인지 확인합니다.
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(port);

        if(portIdentifier.isCurrentlyOwned()){

            System.out.println("Error: Port is currently in use");
        } else {

            //선점되어 있지 않다면 여는데 2000밀리초의 시간만큼 타임아웃을 가집니다.
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if(commPort instanceof SerialPort){

                SerialPort serialPort = (SerialPort) commPort;

                // RS485 통신 표준
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new Reader(in))).start();
                (new Thread(new Writer(out))).start();
            } else {

                System.out.println("Error: Only serial ports are handled by this example");
            }
        }
    }
}
