module sqat::series2::A1a_StatCov

import lang::java::jdt::m3::Core;
import analysis::m3::Core;
import util::Math;

import IO;
import ParseTree;
import String;
import List;
import Set;

import Boolean;
import Relation;
/*

Implement static code coverage metrics by Alves & Visser 
(https://www.sig.eu/en/about-sig/publications/static-estimation-test-coverage)


The relevant base data types provided by M3 can be found here:

- module analysis::m3::Core:

rel[loc name, loc src]        M3.declarations;            // maps declarations to where they are declared. contains any kind of data or type or code declaration (classes, fields, methods, variables, etc. etc.)
rel[loc name, TypeSymbol typ] M3.types;                   // assigns types to declared source code artifacts
rel[loc src, loc name]        M3.uses;                    // maps source locations of usages to the respective declarations
rel[loc from, loc to]         M3.containment;             // what is logically contained in what else (not necessarily physically, but usually also)
list[Message]                 M3.messages;                // error messages and warnings produced while constructing a single m3 model
rel[str simpleName, loc qualifiedName]  M3.names;         // convenience mapping from logical names to end-user readable (GUI) names, and vice versa
rel[loc definition, loc comments]       M3.documentation; // comments and javadoc attached to declared things
rel[loc definition, Modifier modifier] M3.modifiers;     // modifiers associated with declared things

- module  lang::java::m3::Core:

rel[loc from, loc to] M3.extends;            // classes extending classes and interfaces extending interfaces
rel[loc from, loc to] M3.implements;         // classes implementing interfaces
rel[loc from, loc to] M3.methodInvocation;   // methods calling each other (including constructors)
rel[loc from, loc to] M3.fieldAccess;        // code using data (like fields)
rel[loc from, loc to] M3.typeDependency;     // using a type literal in some code (types of variables, annotations)
rel[loc from, loc to] M3.methodOverrides;    // which method override which other methods
rel[loc declaration, loc annotation] M3.annotations;

Tips
- encode (labeled) graphs as ternary relations: rel[Node,Label,Node]
- define a data type for node types and edge types (labels) 
- use the solve statement to implement your own (custom) transitive closure for reachability.

Questions:
- what methods are not covered at all?
- how do your results compare to the jpacman results in the paper? Has jpacman improved?
- use a third-party coverage tool (e.g. Clover) to compare your results to (explain differences)


*/

data Label = label(str typ);

data Node = nod(str typ, loc name, bool tes);

alias G = tuple [Node from, Label edge, Node to];

alias GC = rel [Node from, Label edge, Node to];



set [Node] solveGraph(GC gc) {
	set [Node] coveredMethods = {};
	for(G g <- gc) {
		GC solv = {};
		if(g.from.tes && g.from.typ == "method") {
			solv = domainR(gc, {g.from});
		}
		solve(coveredMethods) {
			coveredMethods += solv.to;
			solv = domainR(gc, solv.to);
		}
	}
	return coveredMethods;
}

set[Node] getNonTestMethods (GC gc) {
	set [Node] totalMethods = {};
	for(G g <- gc) {
		if(g.from.typ == "method" && !g.from.tes) {
		   totalMethods += g.from;
		}
		if(g.to.typ == "method" && !g.to.tes) {
		   totalMethods += g.to;
		}
	}
	
	return totalMethods;
}

void makeAndSolve() {
	M3 m3 = createM3FromEclipseProject(|project://jpacman-framework|);
	
    GC gc  = {};
    
    
    rel [loc from, loc to] testConnections = getTestConnections(m3);
    rel [loc from, loc to] methodConnections = rangeX(m3.methodInvocation, testConnections.to) + rangeX(m3.containment, testConnections.to);
    
    gc = makeGraphPart(gc, methodConnections, false);
    gc += makeGraphPart(gc, testConnections, true);
    set[Node] covered= solveGraph(gc);
    set[Node] allNonTestMethods =  getNonTestMethods(gc);
    
    println("Covered methods: <size(allNonTestMethods & covered)>");

    println("Coverage: <size(allNonTestMethods & covered)> of <size(allNonTestMethods)>");
    //println("Methods not covered <allNonTestMethods - covered>");
}

GC makeGraphPart(GC gc, rel[loc from, loc to] methodConnections, bool tes) {
	for(methodConnection <- methodConnections) {
   		if(methodConnection.from.scheme == "java+package") {
   	       	if(methodConnection.to.scheme == "java+compilationUnit") {
   	       	  	for(tuple [loc from, loc to] c <- domainR(methodConnections, {methodConnection.to})) {
   	       	  		Node from = nod("package", methodConnection.from, tes);
   	       	    	Node to = nod("class", c.to, tes);
   	       	    	Label l = label("DT");
   	       	    	gc += <from, l ,to>;
   	       		}
   	     	}
   	  	}
   	  	if(methodConnection.from.scheme == "java+class" ||  methodConnection.from.scheme == "java+interface" || methodConnection.from.scheme == "java+enum") {
   	  		if(methodConnection.to.scheme == "java+method" || methodConnection.to.scheme == "java+constructor") {
   	  			if(methodConnection.to.path[0..4] == "/nl/") {
   	  				Node from = nod("class", methodConnection.from, tes);
   	  	    		Node to = nod("method", methodConnection.to, tes);
   	  	   	   	 	Label l = label("DM");
   	  	    		gc += <from, l ,to>;
   	  	    	}
   	  		}
   	  		
   	  	}
   	  	if(methodConnection.from.scheme == "java+method" || methodConnection.from.scheme == "java+constructor") {
   	    	if(methodConnection.to.scheme == "java+method" || methodConnection.to.scheme == "java+constructor") {
   	    		if(methodConnection.to.path[0..4] == "/nl/") {
   	    			Node from = nod("method", methodConnection.from, tes);
   	  	   		    Node to = nod("method", methodConnection.to, false);
   	  	    		Label l = label("DC");
   	  	    		gc += <from, l ,to>;
   	  	    	} 
   	    	}	
		}
	}
	return gc;
}



rel [loc from, loc to] getTestConnections (M3 m3) {
	annotations = m3.annotations;
    rel [loc dec, loc ann] tests = rangeR (annotations, {|java+interface:///org/junit/Test|} + {|java+interface:///org/junit/Before|} + {|java+interface:///org/junit/After|} + {|java+interface:///org/junit/When|});
	rel [loc from, loc to] contained = m3.containment;
    rel [loc from, loc to] containedTestMethods = rangeR(contained, tests.dec);
    rel [loc from, loc to] containedTestClasses = rangeR(contained, containedTestMethods.from);
    rel [loc from, loc to] containedCompilationUnits = rangeR(contained, containedTestClasses.from);
    println(containedCompilationUnits);
    rel [loc from, loc to] containedTests = rangeR (domainR(contained, containedTestMethods.from + containedTestClasses.from + containedCompilationUnits.from),
    													containedTestMethods.to + containedTestClasses.to + containedCompilationUnits.to);
    testConnections = containedTests + domainR(m3.methodInvocation, containedTests.to);
    return testConnections;
}









