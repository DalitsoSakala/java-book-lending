package com.booklending.cs3301;

import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



public class TokenAnalyser {
    public static void main(String[] args) {
        try {
            // Create a JavaParser object
            JavaParser javaParser = new JavaParser();

            // Parse the Java source file
            ParseResult<CompilationUnit> parseResult = javaParser.parse(
                    new FileInputStream("src/main/java/com/booklending/cs3301/MainWindow.java"));
            CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

            // Get a list of all the classes in the source file
            List<ClassOrInterfaceDeclaration> classes = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

//            List<ClassOrInterfaceDeclaration> methods = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

            // Print out the name of each class
            for (ClassOrInterfaceDeclaration cls : classes) {
                System.out.println("Class name: " + cls.getNameAsString());
            }


            for (ClassOrInterfaceDeclaration cls : classes) {
                // Get the node corresponding to the class
                Node classNode = (Node) cls;

                // Generate an AST for the class
                Optional<Node> ast = classNode.getChildNodes().stream()
                        .filter(node -> node instanceof TypeDeclaration)
                        .findFirst();

                if (ast.isPresent()) {
                    Node classAst = ast.get();
                    System.out.println("Class AST for " + cls.getNameAsString() + ":");
                    System.out.println(classAst.toString());
                } else {
                    System.out.println("No class AST found for " + cls.getNameAsString());
                }
            }

            int totalTokens = 0;

            for (JavaToken token : compilationUnit.getTokenRange().get()) {
                totalTokens++;
            }

            System.out.println("Total number of tokens: " + totalTokens);

            HashMap<JavaToken.Kind, Integer> tokenCount = new HashMap<>();

            for (JavaToken token : compilationUnit.getTokenRange().get()) {
                JavaToken.Kind kind = JavaToken.Kind.valueOf(token.getKind());
                tokenCount.put(kind, tokenCount.getOrDefault(kind, 0) + 1);
            }

            for (Map.Entry<JavaToken.Kind, Integer> entry : tokenCount.entrySet()) {
                String tokenName = entry.getKey().name();
                int tokenCounts = entry.getValue();
                System.out.println(tokenName + ": " + tokenCounts);
            }












        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}package com.booklending.cs3301;

import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



public class TokenAnalyser {
    public static void main(String[] args) {
        try {
            // Create a JavaParser object
            JavaParser javaParser = new JavaParser();

            // Parse the Java source file
            ParseResult<CompilationUnit> parseResult = javaParser.parse(
                    new FileInputStream("src/main/java/com/booklending/cs3301/MainWindow.java"));
            CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

            // Get a list of all the classes in the source file
            List<ClassOrInterfaceDeclaration> classes = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

//            List<ClassOrInterfaceDeclaration> methods = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);

            // Print out the name of each class
            for (ClassOrInterfaceDeclaration cls : classes) {
                System.out.println("Class name: " + cls.getNameAsString());
            }


            for (ClassOrInterfaceDeclaration cls : classes) {
                // Get the node corresponding to the class
                Node classNode = (Node) cls;

                // Generate an AST for the class
                Optional<Node> ast = classNode.getChildNodes().stream()
                        .filter(node -> node instanceof TypeDeclaration)
                        .findFirst();

                if (ast.isPresent()) {
                    Node classAst = ast.get();
                    System.out.println("Class AST for " + cls.getNameAsString() + ":");
                    System.out.println(classAst.toString());
                } else {
                    System.out.println("No class AST found for " + cls.getNameAsString());
                }
            }

            int totalTokens = 0;

            for (JavaToken token : compilationUnit.getTokenRange().get()) {
                totalTokens++;
            }

            System.out.println("Total number of tokens: " + totalTokens);

            HashMap<JavaToken.Kind, Integer> tokenCount = new HashMap<>();

            for (JavaToken token : compilationUnit.getTokenRange().get()) {
                JavaToken.Kind kind = JavaToken.Kind.valueOf(token.getKind());
                tokenCount.put(kind, tokenCount.getOrDefault(kind, 0) + 1);
            }

            for (Map.Entry<JavaToken.Kind, Integer> entry : tokenCount.entrySet()) {
                String tokenName = entry.getKey().name();
                int tokenCounts = entry.getValue();
                System.out.println(tokenName + ": " + tokenCounts);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
