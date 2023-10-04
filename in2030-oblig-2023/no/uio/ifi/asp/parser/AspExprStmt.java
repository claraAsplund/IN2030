package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspExprStmt extends AspSmallStmt {

    AspExpr ae;

    AspExprStmt(int n) {
        super(n);
    
    }
    static AspExprStmt parse(Scanner s){
        enterParser("expr stmt");

        AspExprStmt aes = new AspExprStmt(s.curLineNum());
            aes.ae = AspExpr.parse(s);

        //System.out.println(aes);    
        leaveParser("expr stmt");
        return aes; 
    }
   
    @Override
    public void prettyPrint(){
        //-- Must be changed in part 2:

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
