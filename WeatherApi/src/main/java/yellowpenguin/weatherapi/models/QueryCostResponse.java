package yellowpenguin.weatherapi.models;

import java.io.Serializable;

public class QueryCostResponse implements Serializable {
	
    private double latitude;
    private double longitude;
    private String resolvedAddress;
    private String address;
    private String timezone;
    private String description;

    public QueryCostResponse () {
    	
    }

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getResolvedAddress() {
		return resolvedAddress;
	}

	public String getAddress() {
		return address;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getDescription() {
		return description;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setResolvedAddress(String resolvedAddress) {
		this.resolvedAddress = resolvedAddress;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "QueryCostResponse [latitude=" + latitude + ", longitude=" + longitude + ", resolvedAddress="
				+ resolvedAddress + ", address=" + address + ", timezone=" + timezone + ", description=" + description
				+ "]";
	}
	
	

    
}