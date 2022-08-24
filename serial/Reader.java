package org.serial;

import java.io.IOException;
import java.io.InputStream;

public class Reader implements Runnable{
    //Runnable로 실행하여 외부에서 지속적으로 값을 받습니다.

    InputStream in; //외부로 부터 데이터를 받아옵니다.

    public Reader(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int len = -1;

        try{
            //보통 계속해서 신호를 받기 때문에 사실상 while문을 빠져나가는 상황은 거의 없습니다.
            //코드가 뽑힌다면 -1이 반환되어 while문은 종료가 될 것입니다.
            while ((len = this.in.read(buffer)) > -1){
                System.out.println(new String(buffer, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
