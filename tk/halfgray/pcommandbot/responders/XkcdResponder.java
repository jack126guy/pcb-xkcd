/* Copyright (c) 2015 Jack126Guy. Refer to /LICENSE.txt for details. */
package tk.halfgray.pcommandbot.responders;

/**
 * Responder that retrieves information about comics from
 * <a href="http://xkcd.com/">xkcd</a>. An argument containing the
 * comic number may be provided; if no argument is provided, or if the
 * argument is malformed, the current comic is retrieved.
 */
public class XkcdResponder implements tk.halfgray.pcommandbot.Responder {
	/**
	 * Create a new XkcdResponder.
	 */
	public XkcdResponder() {
		//Do nothing
	}

	@Override
	public String respond(String channel, String user, String[] mentions, String argument) {
		int comic;
		boolean badnumber = false;
		try {
			comic = Integer.parseInt(argument);
		} catch(NumberFormatException e) {
			comic = 0;
			//Excuse an empty argument
			badnumber = !argument.isEmpty();
		}
		if(comic < 0) {
			badnumber = true;
		}
		String apiurl;
		if(comic == 0) {
			//Get the current comic
			apiurl = "http://xkcd.com/info.0.json";
		} else {
			apiurl = "http://xkcd.com/"+comic+"/info.0.json";
		}
		Object info;
		try {
			info = new org.json.simple.parser.JSONParser().parse(new java.io.InputStreamReader(
				new java.net.URL(apiurl).openConnection().getInputStream()));
		} catch(java.net.MalformedURLException e) {
			return "xkcd: Could not access the information: API URL malformed???";
		} catch(java.io.IOException e) {
			return "xkcd: Could not find comic, or the service is inaccessible";
		} catch(org.json.simple.parser.ParseException e) {
			return "xkcd: Could not process the comic information";
		}
		java.util.Map infomap;
		int number;
		String title;
		String year;
		String month;
		String day;
		try {
			infomap = (java.util.Map) info;
			number = ((Number) infomap.get("num")).intValue();
			title = (String) infomap.get("safe_title");
			year = (String) infomap.get("year");
			month = (String) infomap.get("month");
			day = (String) infomap.get("day");
		} catch(ClassCastException e) {
			return "xkcd: Could not process the comic information";
		}
		String summary = "xkcd #"+number+", \""+title+",\" posted "+year+"/"+month+"/"+day
			+": http://xkcd.com/"+number+"/";
		if(comic == 0) {
			if(badnumber) {
				return "Could not interpret the comic number, got the current comic instead: "+summary;
			} else {
				return "Current comic: "+summary;
			}
		} else {
			return summary;
		}
	}
}
