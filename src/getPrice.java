import core.getFileRouteAndRunUnp4k;
import core.dataCollation;

import java.io.IOException;

import MISC.lever;
import MISC.hello;

public class getPrice {
    public static void main(String[] args) throws IOException {
        hello.world();
        int Lever = lever.lever();
        if(Lever == 0) {
            getFileRouteAndRunUnp4k.Starthere();
            dataCollation.startHere();
        } else if(Lever == 1){
            dataCollation.startHere();
        }
    }
}


