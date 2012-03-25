all:
	javac -classpath .:classes:lib/servlet-api.jar -d classes src/*.java 
	mv classes/*.class /home/iihm/Documents/petit-theatre/apache-tomcat-6/apache-tomcat-6.0.14/webapps/ROOT/WEB-INF/classes
	cd ../apache-tomcat-6/apache-tomcat-6.0.14/bin;./shutdown.sh;./startup.sh