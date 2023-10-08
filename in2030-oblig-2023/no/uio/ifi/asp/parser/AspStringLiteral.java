package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspStringLiteral extends AspAtom{
    String savedStringLit;

    AspStringLiteral(int n) {
        super(n);
    
    }
    public static AspStringLiteral parse(Scanner s){
        enterParser("string literal");
        AspStringLiteral asl = new AspStringLiteral(s.curLineNum());
        asl.savedStringLit = s.curToken().stringLit;

        skip(s,stringToken);

        leaveParser("string literal");
        return asl;
    }

    @Override
    public void prettyPrint(){
        prettyWrite("\"" + savedStringLit + "\"");

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
