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

set[Message] checkStyle(loc project) {
  	set[Message] result = {};
  	
  	return result;
}

set[Message] constantName(loc project, str pattern){
	tree = parseJava(project);
	visit(tree){
		case theMethod:(MethodDec)`<MethodDecHead m> <MethodBody body>`: println(theMethod);
		case expr:(FieldDec)`<FieldMod _> static final <Type _> <VarDecId name>;`: println("<expr>, <name>");
		case expr:(FieldDec)`<FieldMod _> final static <Type _> <VarDecId name>;`: println("<expr>, <name>");
		case expr:(LocalVarDec)`final <Type _> <VarDecId name>`: println("<expr>, <name>");
		case expr:(FieldDec)`<FieldMod _> static final <Type _> <VarDecId name> = <VarInit _>;`: println("<expr@\loc>, <name>");
		case expr:(FieldDec)`<FieldMod _> final static <Type _> <VarDecId name> = <VarInit _>;`: println("<expr@\loc>, <name>");
		case expr:(LocalVarDec)`final <Type _> <VarDecId name> = <VarInit _>`: println("<expr@\loc>, <name>");
	}
	
	return {warning("Hello world", |project://unknown|)};
}

test bool testconstantName() 
	= constantName(|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Inky.java|, "") 
		== {warning("Hello world", |project://unknown|)};