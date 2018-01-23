module sqat::series2::A1b_DynCov

import Java17ish;
import ParseTree;
import util::FileSystem;
import IO;
import String;

/*

Assignment: instrument (non-test) code to collect dynamic coverage data.

- Write a little Java class that contains an API for collecting coverage information
  and writing it to a file. NB: if you write out CSV, it will be easy to read into Rascal
  for further processing and analysis (see here: lang::csv::IO)

- Write two transformations:
  1. to obtain method coverage statistics
     (at the beginning of each method M in class C, insert statement `hit("C", "M")`
  2. to obtain line-coverage statistics
     (insert hit("C", "M", "<line>"); after every statement.)

The idea is that running the test-suite on the transformed program will produce dynamic
coverage information through the insert calls to your little API.

Questions
- use a third-party coverage tool (e.g. Clover) to compare your results to (explain differences)
- which methods have full line coverage?
- which methods are not covered at all, and why does it matter (if so)?
- what are the drawbacks of source-based instrumentation?

Tips:
- create a shadow JPacman project (e.g. jpacman-instrumented) to write out the transformed source files.
  Then run the tests there. You can update source locations l = |project://jpacman/....| to point to the 
  same location in a different project by updating its authority: l.authority = "jpacman-instrumented"; 

- to insert statements in a list, you have to match the list itself in its context, e.g. in visit:
     case (Block)`{<BlockStm* stms>}` => (Block)`{<BlockStm insertedStm> <BlockStm* stms>}` 
  
- or (easier) use the helper function provide below to insert stuff after every
  statement in a statement list.

- to parse ordinary values (int/str etc.) into Java15 syntax trees, use the notation
   [NT]"...", where NT represents the desired non-terminal (e.g. Expr, IntLiteral etc.).  

*/
str method;

str class;

str getMethodName(str m) {
	m = replaceAll(m , "\t", "");
	int parLeft = findFirst(m , "(");
	int parRight = findLast(m, ")");
	int lastSpace = findLast(m[..parLeft], " ");
	return m[lastSpace+1..parRight+1];
}

void insertAll(loc project) {
	for(f <- files(project), f.extension == "java") {
		insertStatements(f, f.file);
	}
}

void insertStatements(loc project, str cl) {
	class = cl;
	tree = parseJava(project);
	newTree = visit(tree) {
		case (CompilationUnit) `<PackageDec? p> <ImportDec* i> <TypeDec* t>` : {
			ImportDec d = [ImportDec] "import coverageApi.Collect;";
			insert (CompilationUnit) `<PackageDec? p><ImportDec d ><ImportDec* i> <TypeDec* t>`;
		}
		
		case (MethodDec)`<MethodDecHead m> {<BlockStm* stms>}` : {
			method = getMethodName(unparse(m));
	   		BlockStm statement = [BlockStm] "Collect.Hit(\"<class>\",\"<method>\");"; 
	   		BlockStm* stms2 = putAfterEvery(stms, BlockStm (loc l) {   											
				BlockStm statement = [BlockStm] "Collect.Hit(\"<class>\",\"<method>\", \"<l.offset>\");"; 
				return statement;
	    	});
	   		MethodBody mb = [MethodBody] "{<statement><stms2>}";
	   		insert (MethodDec)`<MethodDecHead m><MethodBody mb>`;
	   	}
	   	
		case (ConstrDec) `<ConstrHead c>{<ConstrInv? co><BlockStm* stms>}` : {
			method = getMethodName(unparse(c)); 
	   		BlockStm statement = [BlockStm] "Collect.Hit(\"<class>\",\"<method>\");"; 
	   		BlockStm* stms2 = putAfterEvery(stms, BlockStm (loc l) {
				BlockStm statement = [BlockStm] "Collect.Hit(\"<class>\",\"<method>\", \"<l.offset>\");"; 
				return statement;
	    	});
	   		ConstrBody cb = [ConstrBody] "{<co><statement><stms2>}";
	   		insert (ConstrDec)`<ConstrHead c><ConstrBody cb>`;
	    }
	}
	
	newnewTree = visit (newTree) {
		case (MethodBody)`{<BlockStm* stms> return <Expr? e> ; <BlockStm* stms2>}`=>
			(MethodBody)`{<BlockStm* stms> <BlockStm* stms2>return <Expr? e> ; }`
	}
	str newClass = unparse(newnewTree);
	project.authority = "jpacman-instrumented";
	writeFile(project, newClass);
}



// Helper function to deal with concrete statement lists

// second arg should be a closure taking a location (of the element)
// and producing the BlockStm to-be-inserted 
BlockStm* putAfterEvery(BlockStm* stms, BlockStm(loc) f) {
  
  Block put(b:(Block)`{}`) = (Block)`{<BlockStm s>}`
    when BlockStm s := f(b@\loc);
  
  Block put((Block)`{<BlockStm s0>}`) = (Block)`{<BlockStm s0> <BlockStm s>}`
    when BlockStm s := f(s0@\loc);
  
  Block put((Block)`{<BlockStm s0> <BlockStm+ stms>}`) 
    = (Block)`{<BlockStm s0> <BlockStm s> <BlockStm* stms2>}`
    when
      BlockStm s := f(s0@\loc), 
      (Block)`{<BlockStm* stms2>}` := put((Block)`{<BlockStm+ stms>}`);

  if ((Block)`{<BlockStm* stms2>}` := put((Block)`{<BlockStm* stms>}`)) {
    return stms2;
  }
}


