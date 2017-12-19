module sqat::series1::A2_McCabe

import sqat::series1::A1_SLOC;
import analysis::statistics::Correlation;

import lang::java::jdt::m3::AST;
import lang::java::m3::AST;
import IO;

import Set;
import List;
import ListRelation;

/*

Construct a distribution of method cylcomatic complexity. 
(that is: a map[int, int] where the key is the McCabe complexity, and the value the frequency it occurs)


Questions:
- which method has the highest complexity (use the @src annotation to get a method's location)

- how does pacman fare w.r.t. the SIG maintainability McCabe thresholds?

- is code size correlated with McCabe in this case (use functions in analysis::statistics::Correlation to find out)? 
  (Background: Davy Landman, Alexander Serebrenik, Eric Bouwers and Jurgen J. Vinju. Empirical analysis 
  of the relationship between CC and SLOC in a large corpus of Java methods 
  and C functions Journal of Software: Evolution and Process. 2016. 
  http://homepages.cwi.nl/~jurgenv/papers/JSEP-2015.pdf)
  
- what if you separate out the test sources?

Tips: 
- the AST data type can be found in module lang::java::m3::AST
- use visit to quickly find methods in Declaration ASTs
- compute McCabe by matching on AST nodes

Sanity checks
- write tests to check your implementation of McCabe

Bonus
- write visualization using vis::Figure and vis::Render to render a histogram.

*/
set[Declaration] jpacmanASTs() = createAstsFromEclipseProject(|project://jpacman-framework|, true);

alias CC = rel[loc method, int cc];

CC cc(set[Declaration] decls, bool withTests = true) {
	CC result = {};
	
	visit(decls){
		case m:\method(_, _, _, _, Statement impl):
			if(withTests || |project://jpacman-framework/src/main| > m.src){
				result = result + {<m.src, mcCabe(impl)>};
			}
				
		case m:\constructor(_, _, _, Statement impl):
			result = result + {<m.src, mcCabe(impl)>};
	}
	
	return result;
}

alias CCDist = map[int cc, int freq];

CCDist ccDist(CC cc) {
	CCDist result = ();
	for(methodsCC <- cc){
		if(methodsCC.cc notin result)
			result = result + (methodsCC.cc: 1);
		result[methodsCC.cc] = result[methodsCC.cc] + 1;
	}
	return result;
}

int mcCabe(Statement impl) {
	int result = 1;
	visit(impl){
		case \if(Expression condition, Statement thenBranch, Statement elseBranch): 
			result = result + countEdgesOfNode(condition);
		case \if(Expression condition, Statement thenBranch):
			result = result + countEdgesOfNode(condition);
		case \while(Expression condition, Statement body):
			result = result + countEdgesOfNode(condition);
		case \do(Statement body, Expression condition):
			result = result + countEdgesOfNode(condition);
		case \for(_, Expression condition, _, Statement body):
			result = result + countEdgesOfNode(condition);
		case \foreach(_, _, Statement body): result = result + 1;
		case \for(_, _, Statement body): result = result + 1;
	}
	return result;
}

int countEdgesOfNode(Expression condition){
	int result = 1;
	
	visit(condition){
		case infix(_, "&&", _): result = result + 1;
		case infix(_, "||", _): result = result + 1;
	}
	
	return result;
}

real fares(CCDist ccDist, int threshold){
	real smallerThan = 0.0;
	real total = 0.0;
	
	for(complexity <- ccDist){
		if(complexity <= threshold)
			smallerThan = smallerThan + ccDist[complexity];
		total = total + ccDist[complexity];
	}
	
	return smallerThan / total;
}

tuple[loc, int] max(CC cc){
	tuple[loc method,int cc] res = <|project://unknown|, 0>;
	
	for(complexity <- cc){
		if (res.cc < complexity.cc)
			res = complexity;
	}
	return res;
}

list[real] findCorrelation(CC cc, SLOC sloc){
	ccList = [<complexity> | <_, int complexity> <- cc];
	slocList = [sloc[file] | file <- sloc];
	
	println("DEBUG1");
	forCal = ccList join slocList;
	println("DEBUG2");
	
	return PearsonsCorrelationPValues(forCal);
}

test bool testcc()
	= cc(createAstsFromEclipseProject(|project://sqat-analysis|, true))
		== {<|project://sqat-analysis/src/sqat/series1/McCabeTest.java|(53,137,<4,1>,<10,2>), 3>};
test bool testccDist()
	= ccDist(cc(createAstsFromEclipseProject(|project://sqat-analysis|, true)))
		!= (1:1);
