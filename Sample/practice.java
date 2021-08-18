
public class DirectionsApiRequest extends PendingREsultBase<DirectionsResult, DirectionsRequest, DirectionsApi.Response> {
    public DirectionsApiRequest(GeoApiContext context) {
        super(context, DirectionsApi.API_CONFIG, DirectionsApi.Response.class); 
    }
}

protected boolean optimizeWaypoints;
protected Waypoint[] waypoints;

@Override
protected void validateRequest() {
    if (!params().containsKey("origin")) {
        throw new IllegalArgumentException("Request must contain 'origin'");
    }

    if (!params().containsKey("destination")) {
        throw new IllegalArgumentException("Request must contain 'destination'"); 
    }
}