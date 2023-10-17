package no.uio.ifi.asp.runtime;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.parser.AspSyntax;

public class RuntimeIntValue extends RuntimeValue{
    long intValue;

    public RuntimeIntValue(long v){
        intValue = v;
    }

    @Override
    String typeName() {
        return "int";
    }

    @Override
    public String toString(){
        String intVtoStr = intValue + "";
        return intVtoStr;
        
    }
    @Override
    public long getIntValue(String what, AspSyntax where){
        return intValue;
    }






}

