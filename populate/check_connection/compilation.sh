javac -d bin -classpath lib/jus.util.jar:lib/ojdbc14.jar -sourcepath src src/*/*.java 
java -classpath lib/jus.util.jar:lib/ojdbc14.jar:bin application.Theatre
