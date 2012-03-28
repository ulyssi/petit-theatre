prog:
	javac -classpath .:classes:lib/servlet-api.jar:lib/jus.util.jar:lib/ojdbc14.jar -d classes src/*.java src/*/*.java 
	cp -rv classes/* ../apache-tomcat-6/apache-tomcat-6.0.14/webapps/ROOT/WEB-INF/classes
	cd ../apache-tomcat-6/apache-tomcat-6.0.14/bin;./shutdown.sh;./startup.sh

# jesaipas:
# 	javac -classpath .:classes:lib/servlet-api.jar:lib/jus.util.jar:lib/ojdbc14.jar -d classes src/NouvelleRepresentationServlet.java 
# 	mv classes/*.class /home/iihm/Documents/petit-theatre/apache-tomcat-6/apache-tomcat-6.0.14/webapps/ROOT/WEB-INF/classes
# 	cd ../apache-tomcat-6/apache-tomcat-6.0.14/bin;./shutdown.sh;./startup.sh