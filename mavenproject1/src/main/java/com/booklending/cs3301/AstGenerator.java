package com.booklending.cs3301;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.*;
import java.io.File;
import java.io.FileWriter; 
 
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

public class AstGenerator {
    public static void main(String[] args) {
        try {
            JavaParser javaParser = new JavaParser();
            Path currentRelativePath = Paths.get("");
            String sep=System.getProperty("file.separator");
            String s =currentRelativePath.toAbsolutePath().toString()+ sep+"src.main.java".replaceAll("\\.", sep)+sep
                     +new AstGenerator().getClass().getPackageName().replaceAll("\\.", sep)+sep;
            System.out.println("Current absolute path is: " + s);
            // Parse the Java source file
            ParseResult<CompilationUnit> parseResult = javaParser
                    .parse(new FileInputStream(s + "MainWindow.java"));
            CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

            Optional<ClassOrInterfaceDeclaration> mainClass = compilationUnit.getClassByName("MainWindow");
            compilationUnit.findAll(FieldDeclaration.class).stream().filter(f -> f.isPublic() && !f.isStatic()).forEach(
                    f -> System.out.println("Check field at line " + f.getRange().map(r -> r.begin.line).orElse(-1)));

            // System.out.println(mainClass);
            YamlPrinter printer = new YamlPrinter(true);
            try{
            File obj = new File(s + "ast.yml");
            if (obj.createNewFile()) {
                System.out.println("File created: " + obj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter(s+"ast.yml");
            // Writes this content into the specified file
           myWriter.write(printer.output(compilationUnit)); 
            
           // Closing is necessary to retrieve the resources allocated
           myWriter.close(); 
        }catch(IOException e){

        }
            // System.out.println(printer.output(compilationUnit));

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
    /*
     * public static void main(String[] args) {
     * try {
     * // Create a JavaParser object
     * JavaParser javaParser = new JavaParser();
     * Path currentRelativePath = Paths.get("");
     * String s = currentRelativePath.toAbsolutePath().toString() +
     * "\\gradebook\\src\\main\\java\\com\\paradigms\\cs\\";
     * System.out.println("Current absolute path is: " + s);
     * // Parse the Java source file
     * ParseResult<CompilationUnit> parseResult = javaParser.parse(
     * new FileInputStream(s+"App.java"));
     * CompilationUnit compilationUnit = parseResult.getResult().orElse(null);
     * 
     * // Get a list of all the classes in the source file
     * List<ClassOrInterfaceDeclaration> classes =
     * compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
     * 
     * // List<ClassOrInterfaceDeclaration> methods =
     * compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
     * 
     * // Print out the name of each class
     * for (ClassOrInterfaceDeclaration cls : classes) {
     * System.out.println("Class name: " + cls.getNameAsString());
     * }
     * 
     * 
     * for (ClassOrInterfaceDeclaration cls : classes) {
     * // Get the node corresponding to the class
     * Node classNode = (Node) cls;
     * 
     * // Generate an AST for the class
     * Optional<Node> ast = classNode.getChildNodes().stream()
     * .filter(node -> node instanceof TypeDeclaration)
     * .findFirst();
     * 
     * if (ast.isPresent()) {
     * Node classAst = ast.get();
     * System.out.println("Class AST for " + cls.getNameAsString() + ":");
     * System.out.println(classAst.toString());
     * } else {
     * System.out.println("No class AST found for " + cls.getNameAsString());
     * }
     * }
     * 
     * int totalTokens = 0;
     * 
     * for (JavaToken token : compilationUnit.getTokenRange().get()) {
     * totalTokens++;
     * }
     * 
     * System.out.println("Total number of tokens: " + totalTokens);
     * 
     * HashMap<JavaToken.Kind, Integer> tokenCount = new HashMap<>();
     * 
     * for (JavaToken token : compilationUnit.getTokenRange().get()) {
     * JavaToken.Kind kind = JavaToken.Kind.valueOf(token.getKind());
     * tokenCount.put(kind, tokenCount.getOrDefault(kind, 0) + 1);
     * }
     * 
     * for (Map.Entry<JavaToken.Kind, Integer> entry : tokenCount.entrySet()) {
     * String tokenName = entry.getKey().name();
     * int tokenCounts = entry.getValue();
     * System.out.println(tokenName + ": " + tokenCounts);
     * }
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * } catch (FileNotFoundException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
