module sqat::series1::A3_CheckStyle

import Java17ish;
import Message;
import ParseTree;

import util::ResourceMarkers;

import util::FileSystem;
import IO;
/*

Assignment: detect style violations in Java source code.
Select 3 checks out of this list:  http://checkstyle.sourceforge.net/checks.html
Compute a set[Message] (see module Message) containing 
check-style-warnings + location of  the offending source fragment. 

Plus: invent your own style violation or code smell and write a checker.

Note: since concrete matching in Rascal is "modulo Layout", you cannot
do checks of layout or comments (or, at least, this will be very hard).

JPacman has a list of enabled checks in checkstyle.xml.
If you're checking for those, introduce them first to see your implementation
finds them.

Questions
- for each violation: look at the code and describe what is going on? 
  Is it a "valid" violation, or a false positive?

Tips 

- use the grammar in lang::java::\syntax::Java15 to parse source files
  (using parse(#start[CompilationUnit], aLoc), in ParseTree)
  now you can use concrete syntax matching (as in Series 0)

- alternatively: some checks can be based on the M3 ASTs.

- use the functionality defined in util::ResourceMarkers to decorate Java 
  source editors with line decorations to indicate the smell/style violation
  (e.g., addMessageMarkers(set[Message]))

  
Bonus:
- write simple "refactorings" to fix one or more classes of violations 

*/

void main(loc project) {
  addMessageMarkers(checkStyle(project));
}

set[Message] checkStyle(loc project) {
	ms = {};
	for (loc l <- files(project), l.extension == "java") {
		ms += constantName(parse(#start[CompilationUnit], l, allowAmbiguity=true));
		ms += EmptyStatement(parse(#start[CompilationUnit], l, allowAmbiguity=true));
		ms += AvoidInlineConditionals(parse(#start[CompilationUnit], l, allowAmbiguity=true));
	}  
	return ms;
}

bool isCorrectConstant(str name){
	return /^log(ger)?|[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$/ := "<name>";
}

set[Message] AvoidInlineConditionals(start[CompilationUnit] tree){
	set[Message] result = {};
	
	visit(tree){
		case expr:(Expr cond)`<Expr _> <CondMid _> <Expr _>`:
			result += {warning("Inline condition", expr@\loc)};
	}
	return result;
}

set[Message] EmptyStatement(start[CompilationUnit] tree){
	set[Message] result = {};
	
	visit(tree){
		case expr:(Stm empty)`;`:
			result += {warning("Empty statement", expr@\loc)};
	}
	return result;
}

set[Message] constantName(start[CompilationUnit] tree){
	set[Message] result = {};
	
	visit(tree){
		case expr:(FieldDec)`<FieldMod _> static final <Type _> <VarDecId name>;`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
		case expr:(FieldDec)`<FieldMod _> final static <Type _> <VarDecId name>;`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
		case expr:(LocalVarDec)`final <Type _> <VarDecId name>`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
		case expr:(FieldDec)`<FieldMod _> static final <Type _> <VarDecId name> = <VarInit _>;`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
		case expr:(FieldDec)`<FieldMod _> final static <Type _> <VarDecId name> = <VarInit _>;`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
		case expr:(LocalVarDec)`final <Type _> <VarDecId name> = <VarInit _>`:
			if (!isCorrectConstant("<name>"))
				result += {warning("Bad constant naming:<name>", expr@\loc)};
	}
	return result;
}