package yellowpenguin.weatherapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import yellowpenguin.weatherapi.models.dto.WeatherResponseDTO;

public class Weather implements Serializable{

	private double latitude;
	private double longitude;
	private String resolvedAddress;
	private String address;
	private String timezone;
	private String description;

	private LocalDateTime createdAt;

	public Weather() {
	}

	public Weather(WeatherResponseDTO dto) {
		this.latitude = dto.getLatitude();
		this.longitude = dto.getLongitude();
		this.resolvedAddress = dto.getResolvedAddress();
		this.address = dto.getAddress();
		this.timezone = dto.getTimezone();
		this.description = dto.getDescription();
	}

	@Override
	public String toString() {
		return "QueryCostResponse [latitude=" + latitude + ", longitude=" + longitude + ", resolvedAddress="
				+ resolvedAddress + ", address=" + address + ", timezone=" + timezone + ", description=" + description
				+ "]";
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getResolvedAddress() {
		return resolvedAddress;
	}

	public void setResolvedAddress(String resolvedAddress) {
		this.resolvedAddress = resolvedAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}