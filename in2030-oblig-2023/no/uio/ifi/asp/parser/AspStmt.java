// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspStmt extends AspSyntax {
    AspStmt(int n) {
	    super(n);
    }
    public static AspStmt parse(Scanner s){
        enterParser("stmt");

        AspStmt as;
        if(s.curToken().kind == TokenKind.forToken||
        s.curToken().kind == TokenKind.ifToken ||
        s.curToken().kind == TokenKind. whileToken ||
        s.curToken().kind == TokenKind.defToken) {
            as = AspCompoundStmt.parse(s);
        } else {
            as = AspSmallStmtList.parse(s);
        }

        leaveParser("stmt");
        return as;

    }
    public abstract void prettyPrint();

   
}
