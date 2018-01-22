module sqat::series2::A2_CheckArch

import sqat::series2::Dicto;
import lang::java::jdt::m3::Core;
import Message;
import ParseTree;
import IO;
import util::FileSystem;
import Java17ish;
import Set;
import String;


/*

This assignment has two parts:
- write a dicto file (see example.dicto for an example)
  containing 3 or more architectural rules for Pacman
  
- write an evaluator for the Dicto language that checks for
  violations of these rules. 

Part 1  

An example is: ensure that the game logic component does not 
depend on the GUI subsystem. Another example could relate to
the proper use of factories.   

Make sure that at least one of them is violated (perhaps by
first introducing the violation).

Explain why your rule encodes "good" design.
  
Part 2:  
 
Complete the body of this function to check a Dicto rule
against the information on the M3 model (which will come
from the pacman project). 

A simple way to get started is to pattern match on variants
of the rules, like so:

switch (rule) {
  case (Rule)`<Entity e1> cannot depend <Entity e2>`: ...
  case (Rule)`<Entity e1> must invoke <Entity e2>`: ...
  ....
}

Implement each specific check for each case in a separate function.
If there's a violation, produce an error in the `msgs` set.  
Later on you can factor out commonality between rules if needed.

The messages you produce will be automatically marked in the Java
file editors of Eclipse (see Plugin.rsc for how it works).

Tip:
- for info on M3 see series2/A1a_StatCov.rsc.

Questions
- how would you test your evaluator of Dicto rules? (sketch a design)
- come up with 3 rule types that are not currently supported by this version
  of Dicto (and explain why you'd need them). 
*/
/*
Architectuoal roles:
	Game logic cannot be dependable on ui.
*/

M3 m3() = createM3FromEclipseProject(|project://jpacman-framework|);

start[Dicto] dicto() = 
	parse(#start[Dicto], |project://sqat-analysis/src/sqat/series2/checkArch.dicto|, allowAmbiguity=true);

set[Message] eval(start[Dicto] dicto, M3 m3) = eval(dicto.top, m3);

set[Message] eval((Dicto)`<Rule* rules>`, M3 m3) 
  = ( {} | it + eval(r, m3) | r <- rules );

set[Message] eval(Rule rule, M3 m3) {
  set[Message] msgs = {};
  
	switch (rule) {
	  case (Rule)`<Entity e1> must import <Entity e2>`: msgs += mustImport(e1, e2, m3);
	  case (Rule)`<Entity e1> must depend <Entity e2>`: msgs += mustDepend(e1, e2, m3);
	  case (Rule)`<Entity e1> must invoke <Entity e2>`: msgs += mustInvoke(e1, e2, m3);
	  case (Rule)`<Entity e1> must instantiate <Entity e2>`: msgs += mustInstantiate(e1, e2, m3);
	  case (Rule)`<Entity e1> must inherit <Entity e2>`: msgs += mustInherit(e1, e2, m3);
	  
	  case (Rule)`<Entity e1> may import <Entity e2>`: msgs += mayImport(e1, e2, m3);
	  case (Rule)`<Entity e1> may depend <Entity e2>`: msgs += mayDepend(e1, e2, m3);
	  case (Rule)`<Entity e1> may invoke <Entity e2>`: msgs += mayInvoke(e1, e2, m3);
	  case (Rule)`<Entity e1> may instantiate <Entity e2>`: msgs += mayInstantiate(e1, e2, m3);
	  case (Rule)`<Entity e1> may inherit <Entity e2>`: msgs += mayInherit(e1, e2, m3);
	  
	  case (Rule)`<Entity e1> cannot import <Entity e2>`: msgs += cannotImport(e1, e2, m3);
	  case (Rule)`<Entity e1> cannot depend <Entity e2>`: msgs += cannotDepend(e1, e2, m3);
	  case (Rule)`<Entity e1> cannot invoke <Entity e2>`: msgs += cannotInvoke(e1, e2, m3);
	  case (Rule)`<Entity e1> cannot instantiate <Entity e2>`: msgs += cannotInstantiate(e1, e2, m3);
	  case (Rule)`<Entity e1> cannot inherit <Entity e2>`: msgs += cannotInherit(e1, e2, m3);
	  
	  case (Rule)`<Entity e1> can only import <Entity e2>`: msgs += canOnlyImport(e1, e2, m3);
	  case (Rule)`<Entity e1> can only depend <Entity e2>`: msgs += canOnlyDepend(e1, e2, m3);
	  case (Rule)`<Entity e1> can only invoke <Entity e2>`: msgs += canOnlyInvoke(e1, e2, m3);
	  case (Rule)`<Entity e1> can only instantiate <Entity e2>`: msgs += canOnlyInstantiate(e1, e2, m3);
	  case (Rule)`<Entity e1> can only inherit <Entity e2>`: msgs += canOnlyInherit(e1, e2, m3);
	}
  
  return msgs;
}

//-------------------------------------------------------------
set[Message] mustImport(Entity e1, Entity e2, M3 m3){
	
	result = {};
	unitsLoc = "/" + replaceAll("<e1>", ".", "/");
	package = "<e2>";
	
	loc errorsPos;
	
	units = {unit | unit<-m3.containment, unit.from.scheme == "java+compilationUnit", contains(unit.from.path, unitsLoc)};
	
	for(compUnit <- units){
		tree = parse(#start[CompilationUnit], compUnit.from, allowAmbiguity=true);
		errorsPos = tree@\loc;
		visit(tree) {
			case decl:(ImportDec typeImportOnDemandDec)`import <PackageName name>.*;`:{
				if("<name>" == package)
					return {};
			}
		}
	}
	return {error("<e1> must import <e2>", errorsPos)};
}

set[Message] mustDepend(Entity e1, Entity e2, M3 m3){

	dependencies = m3.typeDependency;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(dependency <- dependencies)	{
		if (contains(dependency.from.path, e1Loc) && contains(dependency.to.path, e2Loc)){
			return {};
		}
	}
	
	return {error("<e1> must depend on <e2>", getOneFrom(dependencies).from)};
}

set[Message] mustInvoke(Entity e1, Entity e2, M3 m3){

	invocations = m3.methodInvocation;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e1Loc = replaceAll("<e1Loc>", "::", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	e2Loc = replaceAll("<e2Loc>", "::", "/");
	
	for(invocation <- invocations)	{
		if (contains(invocation.from.path, "<e1Loc>") && contains(invocation.to.path, "<e2Loc>")){
			return {};
		}
	}
	
	return {error("<e1> must invoke <e2>", getOneFrom(invocations).from)};
}

set[Message] mustInstantiate(Entity e1, Entity e2, M3 m3){
	vars = m3.types;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(var <- vars){
		if (contains(var.name.path, "<e1Loc>") && contains("<var.typ>", "<e2Loc>")){
			return {};
		}
	}
	
	return {error("<e1> must instantiate <e2>", getOneFrom(vars).name)};
}

set[Message] mustInherit(Entity e1, Entity e2, M3 m3){
	inheritances = m3.extends;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(inheritance <- inheritances){
		if (contains(inheritance.from.path, "<e1Loc>") && contains(inheritance.to.path, "<e2Loc>")){
			return {};
		}
	}
	
	return {error("<e1> must inherit <e2>", getOneFrom(inheritances).from)};
}

//-------------------------------------------------------------
set[Message] mayImport(Entity e1, Entity e2, M3 m3){
	return {};
}

set[Message] mayDepend(Entity e1, Entity e2, M3 m3){
	return {};
}

set[Message] mayInvoke(Entity e1, Entity e2, M3 m3){
	return {};
}

set[Message] mayInstantiate(Entity e1, Entity e2, M3 m3){
	return {};
}

set[Message] mayInherit(Entity e1, Entity e2, M3 m3){
	return {};
}

//-------------------------------------------------------------
set[Message] cannotImport(Entity e1, Entity e2, M3 m3){
	
	result = {};
	unitsLoc = "/" + replaceAll("<e1>", ".", "/");
	package = "<e2>";
	
	loc errorsPos;
	
	units = {unit | unit<-m3.containment, unit.from.scheme == "java+compilationUnit", contains(unit.from.path, unitsLoc)};
	
	for(compUnit <- units){
		tree = parse(#start[CompilationUnit], compUnit.from, allowAmbiguity=true);
		errorsPos = tree@\loc;
		visit(tree) {
			case decl:(ImportDec typeImportOnDemandDec)`import <PackageName name>.*;`:{
				if("<name>" == package)
					return {error("<e1> cannot import <e2>", errorsPos)};
			}
		}
	}
	return {};
}

set[Message] cannotDepend(Entity e1, Entity e2, M3 m3){
	dependencies = m3.typeDependency;
	
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(dependency <- dependencies)	{
		if (contains(dependency.from.path, e1Loc) && contains(dependency.to.path, e2Loc)){
			return {error("<e1> cannot depend <e2>", dependency.from)};
		}
	}
	return {};
}

set[Message] cannotInvoke(Entity e1, Entity e2, M3 m3){

	invocations = m3.methodInvocation;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e1Loc = replaceAll("<e1Loc>", "::", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	e2Loc = replaceAll("<e2Loc>", "::", "/");
	
	for(invocation <- invocations)	{
		if (contains(invocation.from.path, "<e1Loc>") && contains(invocation.to.path, "<e2Loc>")){
			return {error("<e1> cannot invoke <e2>", invocation.from)};
		}
	}
	
	return {};
}

set[Message] cannotInstantiate(Entity e1, Entity e2, M3 m3){
	vars = m3.types;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(var <- vars){
		if (contains(var.name.path, "<e1Loc>") && contains("<var.typ>", "<e2Loc>")){
			return {error("<e1> cannot instantiate <e2>", var.name)};
		}
	}
	
	return {};
}

set[Message] cannotInherit(Entity e1, Entity e2, M3 m3){
	inheritances = m3.extends;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(inheritance <- inheritances){
		if (contains(inheritance.from.path, "<e1Loc>") && contains(inheritance.to.path, "<e2Loc>")){
			return {error("<e1> cannot inherit <e2>", inheritance.from)};
		}
	}
	
	return {};
}

//-------------------------------------------------------------
set[Message] canOnlyImport(Entity e1, Entity e2, M3 m3){
	
	result = {};
	unitsLoc = "/" + replaceAll("<e1>", ".", "/");
	package = "<e2>";
	
	loc errorsPos;
	
	units = {unit | unit<-m3.containment, unit.from.scheme == "java+compilationUnit", contains(unit.from.path, unitsLoc)};
	
	for(compUnit <- units){
		tree = parse(#start[CompilationUnit], compUnit.from, allowAmbiguity=true);
		errorsPos = tree@\loc;
		visit(tree) {
			case decl:(ImportDec typeImportOnDemandDec)`import <PackageName name>.*;`:{
				if("<name>" != package)
					return {error("<e1> can only import <e2>", errorsPos)};
			}
		}
	}
	return {};
}

set[Message] canOnlyDepend(Entity e1, Entity e2, M3 m3){
	dependencies = m3.typeDependency;
	
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(dependency <- dependencies)	{
		if (contains(dependency.from.path, e1Loc) && !contains(dependency.to.path, e2Loc)){
			return {error("<e1> can only depend on <e2>", dependency.from)};
		}
	}
	return {};
}

set[Message] canOnlyInvoke(Entity e1, Entity e2, M3 m3){

	invocations = m3.methodInvocation;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e1Loc = replaceAll("<e1Loc>", "::", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	e2Loc = replaceAll("<e2Loc>", "::", "/");
	
	for(invocation <- invocations)	{
		if (contains(invocation.from.path, "<e1Loc>") && !contains(invocation.to.path, "<e2Loc>")){
			return {error("<e1> can only invoke <e2>", invocation.from)};
		}
	}
	
	return {};
}

set[Message] canOnlyInstantiate(Entity e1, Entity e2, M3 m3){
	vars = m3.types;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(var <- vars){
		if (contains(var.name.path, "<e1Loc>") && !contains("<var.typ>", "<e2Loc>")){
			return {error("<e1> can only instantiate <e2>", var.name)};
		}
	}
	
	return {};
}

set[Message] canOnlyInherit(Entity e1, Entity e2, M3 m3){
	inheritances = m3.extends;
	e1Loc = "/" + replaceAll("<e1>", ".", "/");
	e2Loc = "/" + replaceAll("<e2>", ".", "/");
	
	for(inheritance <- inheritances){
		if (contains(inheritance.from.path, "<e1Loc>") && !contains(inheritance.to.path, "<e2Loc>")){
			return {error("<e1> can only inherit <e2>", inheritance.from)};
		}
	}
	
	return {};
}