package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;

abstract class AspAtom extends AspSyntax{
    
    AspAtom(int n) {
        super(n);
    
    }
    //kode fra forelesning 2023
    static AspAtom parse(Scanner s){
        AspAtom aa = null;
        switch(s.curToken().kind){
            case falseToken:
                aa = AspBooleanLiteral.parse(s);
                break;
            case trueToken:
                aa = AspBooleanLiteral.parse(s);
                break;
            case floatToken:
                aa = AspFloatLiteral.parse(s);
                break;
            case integerToken:
                aa = AspIntegerLiteral.parse(s);
                break;
            case leftBraceToken:
                aa = AspDictDisplay.parse(s);
                break;
            case leftBracketToken:
                aa = AspListDisplay.parse(s);
                break;

            case leftParToken:
                aa = AspInnerExpr.parse(s);
                break;    

            case nameToken:
                aa = AspName.parse(s);
                break;
            
            case noneToken:
                aa = AspNoneLiteral.parse(s);
                break;
            case stringToken:
                aa = AspStringLiteral.parse(s);
                break;
            
            default:
                parserError("Expected an expression atom but found a " + 
                            s.curToken().kind + "!", s.curLineNum());
            
        }
        return aa;
    }

    @Override
    public void prettyPrint(){
        //må skrives del2

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
}
