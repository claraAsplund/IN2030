package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspWhileStmt extends AspCompoundStmt{

    AspExpr test;
    AspSuite body;

    AspWhileStmt(int n) {
        super(n);
    
    }
    public static AspWhileStmt parse(Scanner s) {
        enterParser("while stmt");

        AspWhileStmt aws = new AspWhileStmt(s.curLineNum());
        
        skip(s,whileToken);
        aws.test = AspExpr.parse(s);
        skip(s,colonToken);
        aws.body = AspSuite.parse(s);

        leaveParser("while stmt");
        return aws;

    }

    @Override
    public void prettyPrint(){
        prettyWrite("while ");
        test.prettyPrint();
        prettyWrite(":");
        body.prettyPrint();

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
