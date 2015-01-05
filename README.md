# PCB-xkcd

PCB-xkcd is a responder for [PCommandBot](http://github.com/jack126guy/pcommandbot) that queries the [xkcd](http://xkcd.com/) API for comic information. 

Without an argument, PCB-xkcd obtains information about the latest comic. An argument can be given with the number of the comic, and information on that comic will be obtained instead.

# Including in an Application

You can include PCB-xkcd in your bot by adding it as a responder.

	bot.getResponders().put("xkcd", new XkcdResponder());

# Building

The source may be found at <http://github.com/jack126guy/pcb-xkcd>.

To build, you will need to download a [PCommandBot binary](https://github.com/jack126guy/pcommandbot/releases) and include it in the classpath:

	javac -classpath [PCB JAR file] XkcdResponder.java

To build a JAR file:

	javac -classpath [PCB JAR file] -d [outside dir] XkcdResponder.java
	cd [outside dir]
	jar cf PCB-xkcd.jar *

# Other Information

The latest version is 1.0.

PCB-xkcd requires [JSON-Simple](https://code.google.com/p/json-simple/). This is included in PCommandBot, but the dependency should be considered when porting.