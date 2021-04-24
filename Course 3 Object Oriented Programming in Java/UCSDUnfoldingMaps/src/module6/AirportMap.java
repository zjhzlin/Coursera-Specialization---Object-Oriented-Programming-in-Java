package module6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 * @author Lynn Zhang
 * 2021-4-24
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	private CommonMarker lastSelected;
	
	public void setup() {
		// setting up PAppler
		size(1000,700, OPENGL);
		
		// setting up map and default events
		//map = new UnfoldingMap(this, 50, 50, 750, 550);
		map = new UnfoldingMap(this, 200, 50, 700, 600);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		// properties: country, altitude, code, city, name
		//AirportMarker airport = new AirportMarker(features.get(1));
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
	
			m.setRadius(5);		
			airportList.add(m);		
			//System.out.println(m.getProperty("country"));
			//System.out.println(m.getProperty("country").equals(airport));
			

			// put airport in hashmap with OpenFlights unique id for key
			airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			routeList.add(sl);
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		
		map.addMarkers(airportList);
		
	}
	
	public void draw() {
		background(0);
		map.draw();
		addButton();
	}

	/* 
	 * Lynn extensions 1. add favourite city button
	 */
	
	private void addButton() {	
		fill(255, 250, 240);
		
		int xbase = 50;
		int ybase = 50;
		
		rect(xbase, ybase, 100, 50);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);		
		text("London", xbase+25, ybase+25);
		
	}
	
	
	// * 2. when click on the button, display only the airports in that city
	// * 3. zoom in on that city
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked()
	{
		int xbase = 50;
		int ybase = 50;
		
		if(mouseX > xbase && mouseX < xbase + 100 && mouseY > ybase && mouseY < ybase + 50) {
			for (Marker a: airportList) {
				AirportMarker am = (AirportMarker) a;
				String[] cityName = ((String) am.getProperty("city")).split("\"");
				if(!cityName[1].equals("London")) {					
					am.setHidden(true);
				}				
			}
			map.zoomAndPanTo(new Location(51.5f, -0.17f), 10);
			
		}
	}
	
	// Extension 5 when mouse is over the airport, display its name
	@Override
	public void mouseMoved() {
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(airportList);
		//loop();
	}
	
	// Extension 5 when mouse is over the airport, display its name
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers) {
			// Abort if there's already a marker selected
			if (lastSelected != null) {
				return;
			}
			
			for (Marker m : markers) 
			{
				CommonMarker marker = (CommonMarker)m;
				if (marker.isInside(map,  mouseX, mouseY)) {
					lastSelected = marker;
					marker.setSelected(true);
					return;
				}
			}
	}
	
	/* 
	 * Lynn extensions:
	 * 1. add favorite city button
	 * 2. when click on the button, display only the airports in that city
	 * 3. zoom in on that city
	 * 4. extension: and display the routes from that city
	 * 5. when mouse is over the airport, display its name
	 * 
	 */
	
	
	
	

}
