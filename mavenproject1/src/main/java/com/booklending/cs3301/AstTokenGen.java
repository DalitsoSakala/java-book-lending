package com.booklending.cs3301;

import com.github.javaparser.JavaParser;
import com.github.javaparser.JavaToken;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.printer.YamlPrinter;
import java.io.File;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstTokenGen {

    public static void main(String[] args) {
        String sourceFiles[] = {"AppState", "Book", "MainWindow", "Person"};
        for (String fileName : sourceFiles) {
            try {
                // Create a JavaParser object
                JavaParser javaParser = new JavaParser();

                // Parse the Java source file
                ParseResult<CompilationUnit> parseResult = javaParser.parse(
                        new FileInputStream(getDir() + fileName + ".java"));
                CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

                analyzeTokens(compilationUnit, fileName);
                generateAstForFile(compilationUnit, fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String getDir(){
        
            Path currentRelativePath = Paths.get("");
            String sep=System.getProperty("file.separator");
            String s =currentRelativePath.toAbsolutePath().toString()+ sep+"src.main.java".replaceAll("\\.", sep)+sep
                     +new AstTokenGen().getClass().getPackageName().replaceAll("\\.", sep)+sep;
            return s;
            
    }

    private static void analyzeTokens(CompilationUnit compilationUnit, String file) throws Exception{

        // Get a list of all the classes in the source file
        List<ClassOrInterfaceDeclaration> classes = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
        BufferedWriter bw=new BufferedWriter(new PrintWriter(getDir()+file+".txt"));
        
        for (ClassOrInterfaceDeclaration cls : classes) {
            bw.write("Class name: " + cls.getNameAsString()+'\n');
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
            bw.write(tokenName + ": " + tokenCounts+'\n');
        }
        bw.flush();
        bw.close();
    }

    private static void generateAstForFile(CompilationUnit compilationUnit, String file) throws Exception {

        // ASTGEN
        String path=getDir();
        YamlPrinter printer = new YamlPrinter(true);
        File obj = new File(path + file+".yaml");
        if (obj.createNewFile()) {
            System.out.println("File created: " + obj.getName());
        } else {
            System.out.println("File already exists.");
        }

        FileWriter myWriter = new FileWriter(path + file+".yaml");
        
        myWriter.write(printer.output(compilationUnit));

        myWriter.close();

    }
}
